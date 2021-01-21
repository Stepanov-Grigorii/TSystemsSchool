package ru.grandstep.logiweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.grandstep.logiweb.dto.OrderDTO;
import ru.grandstep.logiweb.dto.ShowOrderFormDTO;
import ru.grandstep.logiweb.mapper.OrderMapper;
import ru.grandstep.logiweb.model.Order;
import ru.grandstep.logiweb.service.CargoService;
import ru.grandstep.logiweb.service.OrderService;
import ru.grandstep.logiweb.service.WagonService;
import ru.grandstep.logiweb.service.WaypointService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final WagonService wagonService;
    private final WaypointService waypointService;
    private final CargoService cargoService;

    @GetMapping("/list")
    public ModelAndView listOfOrders() {
        List<OrderDTO> orderDTOList = orderService.getAll().stream().map(orderMapper::getOrderDTO).collect(Collectors.toList());
        return new ModelAndView("admin/order/list", "orders", orderDTOList);
    }

    @GetMapping({"/form", "form/{id}"})
    public ModelAndView editOrder(@PathVariable(required = false) Integer id) {
        Order order = id == null ? new Order() : orderService.getById(id);
        ShowOrderFormDTO dto = orderMapper.getShowOrderFormDTO(id, order,
                                                               wagonService.getAll(),
                                                               cargoService.getAll(),
                                                               waypointService.getAll());
        return new ModelAndView("admin/order/form", "orderDTO", dto);
    }

    @PostMapping("/save")
    public RedirectView saveOrder(@ModelAttribute ShowOrderFormDTO orderDTO) {

        Order order = orderMapper.getOrder(orderDTO);

        orderService.save(order);

        return new RedirectView("list");
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.delete(id);
    }
}
