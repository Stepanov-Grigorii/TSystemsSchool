package ru.grandstep.logiweb.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.grandstep.logiweb.dto.OrderDTO;
import ru.grandstep.logiweb.dto.ShowOrderFormDTO;
import ru.grandstep.logiweb.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    public OrderDTO getOrderDTO(Order order){
        OrderDTO dto = new OrderDTO();
        dto.setNumber(order.getNumber());
        dto.setStatus(order.getStatus().getName());
        dto.setDeparture(order.getActionDeparture().getWaypoint().getCity().getName());
        dto.setDestination(order.getActionDestination().getWaypoint().getCity().getName());
        dto.setWagonRegistryNumber(order.getWagon().getRegistryNumber());
        return dto;
    }

    public Order getOrder(ShowOrderFormDTO dto, Wagon wagon, Action action){
        Order order = new Order();

        order.setNumber(dto.getNumber());
        order.setWagon(wagon);
        order.setStatus(Order.Status.WAITING);
        order.setActionDeparture(action);

        return order;
    }

    public ShowOrderFormDTO getShowOrderFormDTO(Integer id, Order order, List<Wagon> wagons,
                                                List<Cargo> cargos, List<Waypoint> waypoints){
        ShowOrderFormDTO dto = new ShowOrderFormDTO();
        dto.setId(id);
        dto.setNumber(order.getNumber());
        dto.setCargoDtoList(cargos.stream()
                .map(cargo -> new ShowOrderFormDTO.CargoDTO(cargo.getId(), cargo.getName()))
                .collect(Collectors.toList()));
        dto.setWaypointDtoList(waypoints.stream()
                .map(waypoint -> new ShowOrderFormDTO.WaypointDTO(waypoint.getId(), waypoint.getName()))
                .collect(Collectors.toList()));
        dto.setWagonDtoList(wagons.stream()
                .map(wagon -> new ShowOrderFormDTO.WagonDTO(wagon.getId(), wagon.getRegistryNumber()))
                .collect(Collectors.toList()));
        return dto;
    }

    public Action getAction(Waypoint waypoint){
        Action action = new Action();
        action.setWaypoint(waypoint);
        return action;
    }
}
