package ru.grandstep.logiweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.checking.DriverCheck;
import ru.grandstep.logiweb.checking.WagonCheck;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.exception.WrongDriverException;
import ru.grandstep.logiweb.exception.WrongIdException;
import ru.grandstep.logiweb.exception.WrongWagonException;
import ru.grandstep.logiweb.model.Action;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.model.Order;
import ru.grandstep.logiweb.repository.ActionRepository;
import ru.grandstep.logiweb.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ActionService actionService;
    private final WagonService wagonService;
    private final DriverService driverService;
    private final WaypointService waypointService;
    private final WagonCheck wagonCheck;
    private final DriverCheck driverCheck;

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

        for (Driver driver : driverService.getAllDriversInWagon(order.getWagon().getId())) {
            if(!driverCheck.check(driver, order)){
                throw new WrongDriverException("Водитель "+ driver.getIdentityNumber() + " не подходит");
            }
        }

        return orderRepository.saveOrUpdate(order);
    }

    public Order update(Order order){
        return orderRepository.saveOrUpdate(order);
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

    public void delete(Integer id) throws WrongIdException {
        if (id == null || id <= 1) {
            throw new WrongIdException();
        }
        orderRepository.delete(id);
    }
}
