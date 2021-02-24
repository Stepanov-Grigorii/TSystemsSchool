package ru.grandstep.logiweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.grandstep.logiweb.checking.WagonCheck;
import ru.grandstep.logiweb.dto.OrderDTO;
import ru.grandstep.logiweb.dto.ShowOrderFormDTO;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.exception.WrongDriverException;
import ru.grandstep.logiweb.exception.WrongIdException;
import ru.grandstep.logiweb.exception.WrongWagonException;
import ru.grandstep.logiweb.mapper.OrderMapper;
import ru.grandstep.logiweb.model.Order;
import ru.grandstep.logiweb.model.Wagon;
import ru.grandstep.logiweb.model.Waypoint;
import ru.grandstep.logiweb.service.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final WagonService wagonService;
    private final WagonCheck wagonCheck;
    private final WaypointService waypointService;
    private final CargoService cargoService;
    private final ActionService actionService;

    @GetMapping("/list")
    public ModelAndView listOfOrders() {
        List<OrderDTO> orderDTOList = orderService.getAll().stream().map(orderMapper::getOrderDTO).collect(Collectors.toList());
        return new ModelAndView("admin/order/list", "orders", orderDTOList);
    }

    @GetMapping({"/form", "form/{id}"})
    public ModelAndView editOrder(@PathVariable(required = false) Integer id) throws WrongIdException, NotFoundException {
        Order order = id == null ? new Order() : orderService.getById(id);
        ShowOrderFormDTO dto = orderMapper.getShowOrderFormDTO(id, order,
                wagonService.getAll(),
                cargoService.getAll(),
                waypointService.getAll());
        return new ModelAndView("admin/order/form", "orderDTO", dto);
    }

    @GetMapping("alternativeform/{id}")
    public ModelAndView newOrder(@PathVariable(name = "id") Integer cargoId) throws NotFoundException, WrongIdException {
        List<Waypoint> waypoints = waypointService.getAll();
        Waypoint departure = actionService.getByCargoId(cargoId).getWaypoint();
        List<Wagon> wagons = wagonCheck.checkedWagonList(wagonService.getAll(), cargoService.getById(cargoId));
        waypoints.remove(departure);

        ShowOrderFormDTO dto = orderMapper.getShowOrderFormDTO2(cargoId, wagons, waypoints);

        return new ModelAndView("admin/order/alternativeform", "orderDTO", dto);
    }

//    @PostMapping("/save")
//    public RedirectView saveOrder(@ModelAttribute ShowOrderFormDTO orderDTO) throws WrongIdException, WrongWagonException, WrongDriverException, NotFoundException {
//        Order order = orderMapper.getOrder(orderDTO);
//        orderService.save(order);
//        return new RedirectView("list");
//    }

    @PostMapping("/save2")
    @ResponseBody
    public RedirectView saveOrder2(@RequestParam(name = "id") Integer cargoId, @ModelAttribute ShowOrderFormDTO orderDTO, BindingResult bindingResult, RedirectAttributes attributes) throws WrongIdException, WrongWagonException, WrongDriverException, NotFoundException {
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.order", bindingResult);
            attributes.addFlashAttribute("order", orderDTO);
            return new RedirectView("alternativeform/{id}");
        }
        orderDTO.setCargoId(cargoId);
        Order order = orderMapper.getOrder(orderDTO);
        orderService.save(order);
        return new RedirectView("list");
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable Integer id) throws WrongIdException {
        orderService.delete(id);
    }
}
