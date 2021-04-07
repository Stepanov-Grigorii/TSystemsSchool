package ru.grandstep.logiweb.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grandstep.logiweb.dto.DriverIntegrationDTO;
import ru.grandstep.logiweb.dto.OrderIntegrationDTO;
import ru.grandstep.logiweb.dto.WagonIntegrationDTO;
import ru.grandstep.logiweb.mapper.DriverMapper;
import ru.grandstep.logiweb.mapper.OrderIntegrationMapper;
import ru.grandstep.logiweb.mapper.WagonMapper;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.model.Order;
import ru.grandstep.logiweb.model.Wagon;
import ru.grandstep.logiweb.service.DriverService;
import ru.grandstep.logiweb.service.OrderService;
import ru.grandstep.logiweb.service.WagonService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class IntegrationRestController {
    private final OrderService orderService;
    private final OrderIntegrationMapper orderIntegrationMapper;
    private final DriverService driverService;
    private final DriverMapper driverMapper;
    private final WagonService wagonService;
    private final WagonMapper wagonMapper;
    private final Logger integrationLogger = LogManager.getLogger(IntegrationRestController.class);

    @GetMapping("/orders")
    public List<OrderIntegrationDTO> getOrders(){
        List<Order> orders = orderService.getAll();
        List<OrderIntegrationDTO> result = orders.stream().map(o -> orderIntegrationMapper.orderToOIM(o, driverService.getAllDriversByOrder(o.getId()))).collect(Collectors.toList());
        return result;
    }

    @GetMapping("/drivers")
    public List<DriverIntegrationDTO> getDrivers(){
        List<Driver> drivers = driverService.getAll();
        List<DriverIntegrationDTO> result = drivers.stream().map(o -> driverMapper.getDriverInterDTO(o, orderService.getByDriverId(o.getId()))).collect(Collectors.toList());
        integrationLogger.info("drivers : " + result);
        return result;
    }

    @GetMapping("/wagons")
    public List<WagonIntegrationDTO> getWagons(){
        List<Wagon> wagons = wagonService.getAll();
        List<WagonIntegrationDTO> result = wagons.stream().map(w -> wagonMapper.getWagonInterDTO(w, orderService.getAllByWagonId(w.getId()))).collect(Collectors.toList());
        integrationLogger.info("wagons : " + result);
        return result;
    }
}
