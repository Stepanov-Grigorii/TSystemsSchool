package ru.grandstep.logiweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.model.Action;
import ru.grandstep.logiweb.model.Order;
import ru.grandstep.logiweb.repository.ActionRepository;
import ru.grandstep.logiweb.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ActionRepository actionRepository;

    public Order getById(Integer id){
        if(id == null || id <= 0){
            throw new RuntimeException("Wrong id");
        }
        return orderRepository.getById(id);
    }

    public List<Order> getAll(){
        return orderRepository.getAll();
    }

    public Order saveOrUpdate(Order order, Action action){
        action.setCargo(order.getActionDeparture().getCargo());
        action.setType(Action.Type.UNLOADING);
        Action savedAction = actionRepository.saveOrUpdate(action);
        order.setActionDestination(savedAction);
        return orderRepository.saveOrUpdate(order);
    }

    public void delete(Integer id) {
        if (id == null || id <= 1) {
            throw new RuntimeException("Wrong id");
        }
        orderRepository.delete(id);
    }
}
