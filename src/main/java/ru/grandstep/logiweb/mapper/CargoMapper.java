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

    public CargoDTO getCargoDTO(Cargo cargo, Waypoint departure, Waypoint destination) {
        CargoDTO dto = new CargoDTO();
        dto.setId(cargo.getId());
        dto.setStatus(cargo.getStatus().getName());
        dto.setName(cargo.getName());
        dto.setDepartureName(departure.getName());
        if(destination != null){
            dto.setDestinationName(destination.getName());
        }
        dto.setWeight(cargo.getWeight());
        dto.setNumber(cargo.getNumber());
        return dto;
    }

    public Cargo getCargo(ShowCargoFormDTO dto) {
        Cargo cargo = new Cargo();
        cargo.setId(dto.getId());
        cargo.setWeight(new BigDecimal(dto.getWeight()));
        cargo.setName(dto.getName());
        cargo.setNumber(dto.getNumber());
        cargo.setStatus(Cargo.Status.PREPARED);
        return cargo;
    }

    public ShowCargoFormDTO getShowCargoFormDTO(Integer id, Cargo cargo, List<Waypoint> waypoints) {
        ShowCargoFormDTO dto = new ShowCargoFormDTO();
        if (cargo.getId() != null) {
            dto.setId(id);
            dto.setName(cargo.getName());
            dto.setNumber(cargo.getNumber());
            dto.setWeight(cargo.getWeight().toString());
        }
        dto.setWaypointDtoList(waypoints.stream()
                .map(waypoint -> new ShowCargoFormDTO.WaypointDTO(waypoint.getId(), waypoint.getName()))
                .collect(Collectors.toList()));

        return dto;
    }

    public Action getAction(ShowCargoFormDTO dto) {
        Action action = new Action();
        Waypoint waypoint = new Waypoint();
        waypoint.setId(dto.getWaypointId());
        action.setWaypoint(waypoint);
        return action;
    }
}
