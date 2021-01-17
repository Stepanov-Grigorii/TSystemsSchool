package ru.grandstep.logiweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.grandstep.logiweb.dto.CargoDTO;
import ru.grandstep.logiweb.dto.ShowCargoFormDTO;
import ru.grandstep.logiweb.mapper.CargoMapper;
import ru.grandstep.logiweb.model.Action;
import ru.grandstep.logiweb.model.Cargo;
import ru.grandstep.logiweb.service.CargoService;
import ru.grandstep.logiweb.service.WaypointService;

@Controller
@RequestMapping("cargoes")
@RequiredArgsConstructor
public class CargoController {
    private final CargoService cargoService;
    private final WaypointService waypointService;
    private final CargoMapper cargoMapper;

    @GetMapping("/list")
    public ModelAndView listOfCargoes(){
        return new ModelAndView("admin/cargo/list", "cargoes", cargoService.getAll());
    }

    @GetMapping({"/form", "form/{id}"})
    public ModelAndView editCargo(@PathVariable(required = false) Integer id){
        Cargo cargo = id == null ? new Cargo() : cargoService.getById(id);
        ShowCargoFormDTO dto = cargoMapper.getShowCargoFormDTO(id, cargo, waypointService.getAll());
        return new ModelAndView("admin/cargo/form", "cargoDTO", dto);
    }

    @PostMapping("/save")
    public RedirectView saveCargo(@ModelAttribute ShowCargoFormDTO dto){
        Action action = cargoMapper.getAction(waypointService.getById(dto.getWaypointId()));
        cargoService.saveOrUpdate(cargoMapper.getCargo(dto), action);
        return new RedirectView("list");
    }

    @DeleteMapping("{id}")
    public void deleteCargo(@PathVariable Integer id){
        cargoService.delete(id);
    }
}
