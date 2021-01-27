package ru.grandstep.logiweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.model.Waypoint;
import ru.grandstep.logiweb.repository.WaypointRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WaypointService {
    private final WaypointRepository waypointRepository;

    public List<Waypoint> getAll(){
        return waypointRepository.getAll();
    }
    public Waypoint getById(Integer id) throws NotFoundException {
        return waypointRepository.getById(id);
    }
}
