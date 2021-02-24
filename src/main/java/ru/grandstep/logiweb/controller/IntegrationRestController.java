package ru.grandstep.logiweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grandstep.logiweb.dto.OrderIntegrationDTO;
import ru.grandstep.logiweb.mapper.OrderIntegrationMapper;
import ru.grandstep.logiweb.model.Order;
import ru.grandstep.logiweb.service.DriverService;
import ru.grandstep.logiweb.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class IntegrationRestController {
    private final OrderService orderService;
    private final OrderIntegrationMapper orderIntegrationMapper;
    private final DriverService driverService;

    @GetMapping("/orders")
    public List<OrderIntegrationDTO> getOrders(){
        List<Order> orders = orderService.getAll();
        List<OrderIntegrationDTO> result = orders.stream().map(o -> orderIntegrationMapper.orderToOIM(o, driverService.getAllDriversByOrder(o.getId()))).collect(Collectors.toList());
        return result;
    }
}
