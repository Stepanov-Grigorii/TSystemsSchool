package ru.grandstep.logiweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.model.City;
import ru.grandstep.logiweb.repository.CityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public City getById(Integer id) throws NotFoundException {
        return cityRepository.getById(id);
    }

    public List<City> getAll(){
        return cityRepository.getAll();
    }
}
