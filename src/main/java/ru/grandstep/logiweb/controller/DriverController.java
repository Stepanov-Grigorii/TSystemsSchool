package ru.grandstep.logiweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.service.DriverService;

@Controller
@RequestMapping("drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping("/list")
    public ModelAndView listOfDrivers(){
        return new ModelAndView("admin/driver/list", "drivers", driverService.getAll());
    }

    @GetMapping({"/form", "form/{id}"})
    public ModelAndView editDriver(@PathVariable(required = false) Integer id){
        Driver driver = id == null ? new Driver() : driverService.getById(id);
        return new ModelAndView("admin/driver/form", "driver", driver);
    }

    @PostMapping("/save")
    public RedirectView saveDriver(@ModelAttribute Driver driver){
        driverService.saveOrUpdate(driver);
        return new RedirectView("list");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteDriver(@PathVariable Integer id){
        driverService.delete(id);
        return new RedirectView("../list");
    }
}
