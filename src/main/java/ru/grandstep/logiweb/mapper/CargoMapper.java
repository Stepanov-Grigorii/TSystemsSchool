package ru.grandstep.logiweb.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.grandstep.logiweb.dto.CargoDTO;
import ru.grandstep.logiweb.dto.ShowCargoFormDTO;
import ru.grandstep.logiweb.model.Action;
import ru.grandstep.logiweb.model.Cargo;
import ru.grandstep.logiweb.model.Waypoint;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CargoMapper {

    public CargoDTO getCargoDTO(Cargo cargo){
        CargoDTO dto = new CargoDTO();
        dto.setStatus(cargo.getStatus().getName());
        dto.setName(cargo.getName());
        dto.setWeight(cargo.getWeigh().toString());
        dto.setNumber(cargo.getNumber());
        return dto;
    }

    public Cargo getCargo(ShowCargoFormDTO dto){
        Cargo cargo = new Cargo();
        cargo.setWeigh(new BigDecimal(dto.getWeight()));
        cargo.setName(dto.getName());
        cargo.setNumber(dto.getNumber());
        cargo.setStatus(Cargo.Status.PREPARED);
        return cargo;
    }

    public ShowCargoFormDTO getShowCargoFormDTO(Integer id, Cargo cargo, List<Waypoint> waypoints){
        ShowCargoFormDTO dto = new ShowCargoFormDTO();
        dto.setId(id);
        dto.setName(cargo.getName());
        dto.setNumber(cargo.getNumber());
        dto.setWaypointDtoList(waypoints.stream()
                .map(waypoint -> new ShowCargoFormDTO.WaypointDTO(waypoint.getId(), waypoint.getName()))
                .collect(Collectors.toList()));
        return dto;
    }

    public Action getAction(Waypoint waypoint){
        Action action = new Action();
        action.setWaypoint(waypoint);
        return action;
    }
}
