package ru.grandstep.logiweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.grandstep.logiweb.dto.DriverDTO;
import ru.grandstep.logiweb.mapper.DriverMapper;
import ru.grandstep.logiweb.model.Cargo;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.model.Order;
import ru.grandstep.logiweb.model.Wagon;
import ru.grandstep.logiweb.repository.DistanceRepository;
import ru.grandstep.logiweb.service.CargoService;
import ru.grandstep.logiweb.service.DriverService;
import ru.grandstep.logiweb.service.OrderService;
import ru.grandstep.logiweb.service.WagonService;

@Controller
@RequestMapping("user/drivers")
@RequiredArgsConstructor
public class UDriverController {
    private final DriverService driverService;
    private final WagonService wagonService;
    private final DriverMapper driverMapper;
    private final OrderService orderService;
    private final CargoService cargoService;
    private final DistanceRepository distanceRepository;

    @GetMapping("/info/{id}")
    public ModelAndView infoDriver(@PathVariable Integer id) {
        DriverDTO dto;
        if (driverService.getById(id).getWagon() == null) {
            dto = driverMapper.getDriverDTOForInfo(driverService.getById(id), orderService.getByDriverId(id), null);
        } else {
            dto = driverMapper.getDriverDTOForInfo(driverService.getById(id), orderService.getByDriverId(id),
                    driverService.getAllDriversInWagon(driverService.getById(id).getWagon().getId()));
        }
        return new ModelAndView("user/driver/info", "driver", dto);
    }

    @PostMapping("/order-status")
    @ResponseBody
    public RedirectView changeOrderStatus(@RequestParam(name = "id") Integer driverId, @RequestParam(name = "number") String orderNumber) {
        Order order = orderService.getByNumber(orderNumber);
        Cargo cargo = cargoService.getById(order.getActionDeparture().getCargo().getId());
        Driver driver = driverService.getById(driverId);
        Wagon wagon = wagonService.getById(driver.getWagon().getId());
        Integer hours = driver.getHoursInCurrentMonth();

        if (order.getStatus() == Order.Status.WAITING) {
            cargo.setStatus(Cargo.Status.SHIPPED);
            order.setStatus(Order.Status.PROCESS);
            orderService.update(order);
            cargoService.update(cargo);
        } else if (order.getStatus() == Order.Status.PROCESS) {

            cargo.setStatus(Cargo.Status.DELIVERED);
            order.setStatus(Order.Status.COMPLETED);
            wagon.setCurrentCity(order.getActionDestination().getWaypoint().getCity());

            wagonService.saveOrUpdate(wagon);
            orderService.update(order);
            cargoService.update(cargo);

            hours += distanceRepository.getByCities(order.getActionDeparture().getWaypoint().getCity(),
                    order.getActionDestination().getWaypoint().getCity()).getHours();
            driver.setHoursInCurrentMonth(hours);
            driverService.saveOrUpdate(driver);
        }

        return new RedirectView("info/" + driverId);
    }
}
