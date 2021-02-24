package ru.grandstep.logiweb.mapper;

import org.springframework.stereotype.Component;
import ru.grandstep.logiweb.dto.OrderIntegrationDTO;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.model.Order;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderIntegrationMapper {
    public OrderIntegrationDTO orderToOIM(Order order, List<Driver> drivers){
        OrderIntegrationDTO dto = new OrderIntegrationDTO();
        dto.setTime(order.getTime());
        dto.setId(order.getId());
        dto.setOrderNumber(order.getNumber());
        dto.setOrderStatus(order.getStatus().getName());
        dto.setDriverIdentityNumbers(drivers.stream().map(Driver::getIdentityNumber).collect(Collectors.toList()));
        dto.setWagonRegistryNumber(order.getWagon().getRegistryNumber());
        dto.setDeparture(order.getActionDeparture().getWaypoint().getName());
        dto.setDestination(order.getActionDestination().getWaypoint().getName());
        dto.setCargoName(order.getActionDeparture().getCargo().getName());
        dto.setCargoNumber(order.getActionDeparture().getCargo().getNumber());

        return dto;
    }
}
