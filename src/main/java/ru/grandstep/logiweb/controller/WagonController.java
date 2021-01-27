package ru.grandstep.logiweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.grandstep.logiweb.dto.ShowWagonFormDTO;
import ru.grandstep.logiweb.dto.WagonDTO;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.exception.WrongIdException;
import ru.grandstep.logiweb.mapper.WagonMapper;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.model.Wagon;
import ru.grandstep.logiweb.service.CityService;
import ru.grandstep.logiweb.service.DriverService;
import ru.grandstep.logiweb.service.OrderService;
import ru.grandstep.logiweb.service.WagonService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/wagons")
@RequiredArgsConstructor
public class WagonController {
    private final WagonService wagonService;
    private final WagonMapper wagonMapper;
    private final CityService cityService;
    private final OrderService orderService;
    private final DriverService driverService;

    @GetMapping("/list")
    public ModelAndView listOfWagons(){
        List<WagonDTO> wagonDTOList = wagonService.getAll().stream().map(wagonMapper::getWagonDTO).collect(Collectors.toList());
        wagonDTOList = wagonMapper.setOrderToWagonDTO(wagonDTOList, orderService.getAll());
        wagonDTOList = wagonMapper.setDriversToWagonDTO(wagonDTOList, driverService.getAll());
        return new ModelAndView("admin/wagon/list", "wagons", wagonDTOList);
    }

    @GetMapping({"/form", "form/{id}"})
    public ModelAndView editWagon(@PathVariable(required = false) Integer id) throws WrongIdException, NotFoundException {
        Wagon wagon = id == null ? new Wagon() : wagonService.getById(id);
        List<Driver> driverList = driverService.getAllFreeDrivers();
        driverList.addAll(driverService.getAllDriversInWagon(id));
        ShowWagonFormDTO dto = wagonMapper.getShowWagonFormDTO(id, wagon, cityService.getAll(), driverList);
        return new ModelAndView("admin/wagon/form", "wagonDTO", dto);
    }

    @PostMapping("/save")
    public RedirectView saveWagon(@ModelAttribute ShowWagonFormDTO wagonDTO) throws WrongIdException, NotFoundException {
        Wagon wagon = wagonMapper.getWagon(wagonDTO);
        Wagon savedWagon = wagonService.saveOrUpdate(wagon);
        Driver driver;

        for (Driver d : driverService.getAllDriversInWagon(savedWagon.getId())) {
            d.setWagon(null);
            driverService.saveOrUpdate(d);
        }

        for (Integer driverId : wagonDTO.getDriverIds()) {
            driver = driverService.getById(driverId);
            driver.setWagon(savedWagon);
            driver.setCurrentCity(savedWagon.getCurrentCity());
            driverService.saveOrUpdate(driver);
        }

        return new RedirectView("list");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteWagon(@PathVariable Integer id) throws WrongIdException, NotFoundException {
        wagonService.delete(id);
        return new RedirectView("../list");
    }
}
