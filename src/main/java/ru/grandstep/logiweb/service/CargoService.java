package ru.grandstep.logiweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.dto.CargoDTO;
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
    private final ActionRepository actionRepository;
    private final WaypointService waypointService;

    public Cargo getById(Integer id){
        if(id == null || id <= 0){
            throw new RuntimeException("Wrong id");
        }
        return cargoRepository.getById(id);
    }

    public List<Cargo> getAll(){
        return cargoRepository.getAll();
    }

    public Cargo saveOrUpdate(Cargo cargo, Action action){
        Cargo savedCargo = cargoRepository.saveOrUpdate(cargo);
        action.setWaypoint(waypointService.getById(action.getWaypoint().getId()));
        action.setCargo(savedCargo);
        action.setType(Action.Type.LOADING);
        actionRepository.saveOrUpdate(action);
        return savedCargo;
    }

    public void delete(Integer id) {
        if (id == null || id <= 1) {
            throw new RuntimeException("Wrong id");
        }
        cargoRepository.delete(id);
    }
}
