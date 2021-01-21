package ru.grandstep.logiweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.grandstep.logiweb.dto.DriverDTO;
import ru.grandstep.logiweb.mapper.DriverMapper;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.service.DriverService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;
    private final DriverMapper driverMapper;

    @GetMapping("/list")
    public ModelAndView listOfDrivers(){
        List<DriverDTO> driverDTOList = driverService.getAll().stream().map(driverMapper::getDriverDTO).collect(Collectors.toList());
        return new ModelAndView("admin/driver/list", "drivers", driverDTOList);
    }

    @GetMapping({"/form", "form/{id}"})
    public ModelAndView editDriver(@PathVariable(required = false) Integer id){
        Driver driver = id == null ? new Driver() : driverService.getById(id);
        DriverDTO dto = driverMapper.getDriverDTO(driver);
        return new ModelAndView("admin/driver/form", "driver", dto);
    }

    @PostMapping("/save")
    public RedirectView saveDriver(@ModelAttribute DriverDTO driverDTO){
        Driver driver = driverMapper.getDriver(driverDTO);
        driverService.saveOrUpdate(driver);
        return new RedirectView("list");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteDriver(@PathVariable Integer id){
        driverService.delete(id);
        return new RedirectView("../list");
    }
}
