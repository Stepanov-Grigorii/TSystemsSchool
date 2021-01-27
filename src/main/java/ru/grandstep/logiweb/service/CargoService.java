package ru.grandstep.logiweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.dto.CargoDTO;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.exception.WrongIdException;
import ru.grandstep.logiweb.mapper.CargoMapper;
import ru.grandstep.logiweb.model.Action;
import ru.grandstep.logiweb.model.Cargo;
import ru.grandstep.logiweb.repository.ActionRepository;
import ru.grandstep.logiweb.repository.CargoRepository;
import ru.grandstep.logiweb.repository.WaypointRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CargoService {
    private final CargoRepository cargoRepository;
    private final ActionRepository actionService;
    private final WaypointService waypointService;

    public Cargo getById(Integer id) throws WrongIdException, NotFoundException {
        if(id == null || id <= 0){
            throw new WrongIdException();
        }
        return cargoRepository.getById(id);
    }

    public List<Cargo> getAll(){
        return cargoRepository.getAll();
    }

    public Cargo saveOrUpdate(Cargo cargo, Action action) throws NotFoundException {
        Cargo savedCargo = cargoRepository.saveOrUpdate(cargo);
        action.setWaypoint(waypointService.getById(action.getWaypoint().getId()));
        action.setCargo(savedCargo);
        action.setType(Action.Type.LOADING);
        actionService.saveOrUpdate(action);
        return savedCargo;
    }

    public Cargo update(Cargo cargo){
        return cargoRepository.saveOrUpdate(cargo);
    }

    public void delete(Integer id) throws WrongIdException {
        if (id == null || id < 1) {
            throw new WrongIdException();
        }
        actionService.delete(actionService.getByCargoId(id).getId());
        cargoRepository.delete(id);
    }
}
