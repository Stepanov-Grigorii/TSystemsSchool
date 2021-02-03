package ru.grandstep.logiweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.grandstep.logiweb.dto.CargoDTO;
import ru.grandstep.logiweb.dto.ShowCargoFormDTO;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.exception.WrongIdException;
import ru.grandstep.logiweb.mapper.CargoMapper;
import ru.grandstep.logiweb.model.Action;
import ru.grandstep.logiweb.model.Cargo;
import ru.grandstep.logiweb.model.Waypoint;
import ru.grandstep.logiweb.service.ActionService;
import ru.grandstep.logiweb.service.CargoService;
import ru.grandstep.logiweb.service.WaypointService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/cargoes")
@RequiredArgsConstructor
public class CargoController {
    private final CargoService cargoService;
    private final ActionService actionService;
    private final WaypointService waypointService;
    private final CargoMapper cargoMapper;

    @GetMapping("/list")
    public ModelAndView listOfCargoes() {
        List<CargoDTO> cargoDTOList = new ArrayList<>();
        List<Cargo> cargos = cargoService.getAll();
        Action actionDeparture;
        Action actionDestination;
        Waypoint departure;
        Waypoint destination = null;
        //в этом цикле формируется cargoDTOList, в котором ставится в соответсвие груз и действия.
        for (Cargo cargo : cargos) {
            actionDeparture = actionService.getByCargoIdAndType(cargo.getId(), Action.Type.LOADING);
            actionDestination = actionService.getByCargoIdAndType(cargo.getId(), Action.Type.UNLOADING);
            departure = actionDeparture.getWaypoint();
            if (actionDestination.getWaypoint() != null) {
                destination = actionDestination.getWaypoint();
            } else {
                destination = null;
            }
            cargoDTOList.add(cargoMapper.getCargoDTO(cargo,
                    departure,
                    destination));
        }

        //List<CargoDTO> cargoDTOList = cargoService.getAll().stream().map(cargoMapper::getCargoDTO).collect(Collectors.toList());
        return new ModelAndView("admin/cargo/list", "cargoes", cargoDTOList);
    }

    @GetMapping({"/form", "form/{id}"})
    public ModelAndView editCargo(@PathVariable(required = false) Integer id) throws WrongIdException, NotFoundException {
        Cargo cargo = id == null ? new Cargo() : cargoService.getById(id);
        ShowCargoFormDTO dto = cargoMapper.getShowCargoFormDTO(id, cargo, waypointService.getAll());
        return new ModelAndView("admin/cargo/form", "cargoDTO", dto);
    }

    @PostMapping("/save")
    public RedirectView saveCargo(@ModelAttribute ShowCargoFormDTO dto) throws NotFoundException, WrongIdException {
        Cargo cargo = cargoMapper.getCargo(dto);
        Action action = cargoMapper.getAction(dto);
        if (cargo.getId() != null) {
            cargoService.delete(cargo.getId());
        }
        cargoService.saveOrUpdate(cargo, action);
        return new RedirectView("list");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteCargo(@PathVariable Integer id) throws WrongIdException {
        cargoService.delete(id);
        return new RedirectView("../list");
    }
}
