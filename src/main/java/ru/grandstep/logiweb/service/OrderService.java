package ru.grandstep.logiweb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.checking.DriverCheck;
import ru.grandstep.logiweb.checking.WagonCheck;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.exception.WrongDriverException;
import ru.grandstep.logiweb.exception.WrongIdException;
import ru.grandstep.logiweb.exception.WrongWagonException;
import ru.grandstep.logiweb.integration.ActivemqProducer;
import ru.grandstep.logiweb.mapper.OrderIntegrationMapper;
import ru.grandstep.logiweb.model.Action;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.model.Order;
import ru.grandstep.logiweb.repository.ActionRepository;
import ru.grandstep.logiweb.repository.OrderRepository;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final ActionService actionService;
    private final WagonService wagonService;
    private final DriverService driverService;
    private final WaypointService waypointService;
    private final WagonCheck wagonCheck;
    private final DriverCheck driverCheck;
    private final ActivemqProducer producer;

    public Order getById(Integer id) throws WrongIdException, NotFoundException {
        if(id == null || id <= 0){
            throw new WrongIdException();
        }
        return orderRepository.getById(id);
    }

    public List<Order> getAll(){
        return orderRepository.getAll();
    }

//    public Order saveOrUpdate(Order order, Action action){
//        action.setCargo(order.getActionDeparture().getCargo());
//        action.setType(Action.Type.UNLOADING);
//        Action savedAction = actionRepository.saveOrUpdate(action);
//        order.setActionDestination(savedAction);
//        return orderRepository.saveOrUpdate(order);
//    }

    /**
     * @param order
     * @return
     * @throws WrongIdException
     * @throws WrongWagonException
     * @throws WrongDriverException
     * @throws NotFoundException
     */
    public Order save(Order order) throws WrongIdException, WrongWagonException, WrongDriverException, NotFoundException {

        order.setActionDeparture(actionService.getByCargoId(order.getActionDeparture().getId()));
        if(!wagonCheck.check(wagonService.getById(order.getWagon().getId()), order.getActionDeparture().getCargo())){
            throw new WrongWagonException();
        }

        order.setWagon(wagonService.getById(order.getWagon().getId()));

        Action action = order.getActionDestination();
        action.setWaypoint(waypointService.getById(action.getWaypoint().getId()));
        action.setCargo(order.getActionDeparture().getCargo());
        action.setType(Action.Type.UNLOADING);
        Action savedAction = actionService.saveOrUpdate(action);

        order.setActionDestination(savedAction);

        //генерация номеров для заказа.
        order.setNumber(getOrderId());

        for (Driver driver : driverService.getAllDriversInWagon(order.getWagon().getId())) {
            if(!driverCheck.check(driver, order)){
                throw new WrongDriverException("Водитель "+ driver.getIdentityNumber() + " не подходит");
            }
        }

        Order savedOrder = orderRepository.saveOrUpdate(order);
        producer.sendOrder(savedOrder);
        return savedOrder;
    }

    public Order update(Order order){
        Order savedOrder = orderRepository.saveOrUpdate(order);
        producer.sendOrder(savedOrder);
        return savedOrder;
    }

    public List<Order> getByDriverId(Integer driverId){
        return orderRepository.getByDriverId(driverId);
    }

    public List<Order> getAllByWagonId(Integer wagonId){
        return orderRepository.getAllByWagonId(wagonId);
    }

    public Order getByNumber(String number){
        return orderRepository.getByNumber(number);
    }

    public String getOrderId(){
        return generateId(orderRepository.getOrderIds());
    }

    private String generateId(List<String> ids){
        String uniqId = "";

        uniqId += (char)(new Random().nextInt(26) + 'A');
        uniqId += (char)(new Random().nextInt(26) + 'A');
        uniqId += (char)(new Random().nextInt(26) + 'A');
        if(!ids.isEmpty() && ids.contains(uniqId)){
            return generateId(ids);
        }
        return uniqId;
    }

    public void delete(Integer id) throws WrongIdException {
        if (id == null || id <= 1) {
            throw new WrongIdException();
        }
        orderRepository.delete(id);
    }
}
