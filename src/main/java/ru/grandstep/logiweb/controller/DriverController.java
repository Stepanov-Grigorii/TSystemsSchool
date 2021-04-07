package ru.grandstep.logiweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.grandstep.logiweb.checking.DriverCheck;
import ru.grandstep.logiweb.dto.DriverDTO;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.exception.WrongIdException;
import ru.grandstep.logiweb.mapper.DriverMapper;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.service.DriverService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;
    private final DriverMapper driverMapper;
    private final DriverCheck driverCheck;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public ModelAndView listOfDrivers() {
        List<DriverDTO> driverDTOList = driverService.getAll().stream().map(driverMapper::getDriverDTO).collect(Collectors.toList());
        return new ModelAndView("admin/driver/list", "drivers", driverDTOList);
    }

    @GetMapping({"/form", "form/{id}"})
    public ModelAndView editDriver(@PathVariable(required = false) Integer id, Model model) throws WrongIdException, NotFoundException {
        if (model.containsAttribute("driver")) {
            return new ModelAndView("admin/driver/form", "driver", Objects.requireNonNull(model.getAttribute("driver")));
        }
        Driver driver = id == null ? new Driver() : driverService.getById(id);
        DriverDTO dto = driverMapper.getDriverDTO(driver);
        return new ModelAndView("admin/driver/form", "driver", dto);
    }

    @PostMapping("/save")
    public RedirectView saveDriver(@ModelAttribute @Valid DriverDTO driverDTO, BindingResult bindingResult, RedirectAttributes attributes) throws NotFoundException {
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.driver", bindingResult);
            attributes.addFlashAttribute("driver", driverDTO);
            return new RedirectView("form");
        }
        Driver driver = driverMapper.getDriver(driverDTO);
        if (driver.getPassword() != null) {
            driver.setPassword(passwordEncoder.encode(driver.getPassword()));
        }

        driverService.update(driver);
        return new RedirectView("list");
    }

//    @PostMapping("/save2")
//    public RedirectView saveDriver2(@ModelAttribute @Valid DriverDTO driverDTO, BindingResult bindingResult, RedirectAttributes attributes) throws NotFoundException {
//        if (bindingResult.hasErrors()) {
//            attributes.addFlashAttribute("org.springframework.validation.BindingResult.driver", bindingResult);
//            attributes.addFlashAttribute("driver", driverDTO);
//            return new RedirectView("form");
//        }
//        Driver driver = driverMapper.getDriver(driverDTO);
//        if (driver.getPassword() != null) {
//            driver.setPassword(passwordEncoder.encode(driver.getPassword()));
//        }
//
//        driverService.update(driver);
//        return new RedirectView("list");
//    }

    @PostMapping("/change-password")
    public RedirectView changePassword(@ModelAttribute DriverDTO driverDTO) throws NotFoundException {
        driverService.update(driverMapper.getDriver(driverDTO));
        return new RedirectView("list");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteDriver(@PathVariable Integer id) throws WrongIdException {
        driverService.delete(id);
        return new RedirectView("../list");
    }
}
