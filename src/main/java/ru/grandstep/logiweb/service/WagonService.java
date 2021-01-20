package ru.grandstep.logiweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.model.Wagon;
import ru.grandstep.logiweb.repository.WagonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WagonService {
    private final WagonRepository wagonRepository;
    private final CityService cityService;

    public Wagon getById(Integer id){
        if(id == null || id <= 0){
            throw new RuntimeException("Wrong id");
        }
        return wagonRepository.getById(id);
    }

    public List<Wagon> getAll(){
        return wagonRepository.getAll();
    }

    public Wagon saveOrUpdate(Wagon wagon){
        if(wagon.getCurrentCity().getName() == null){
            wagon.setCurrentCity(cityService.getById(wagon.getCurrentCity().getId()));
        }
        return wagonRepository.saveOrUpdate(wagon);
    }

    public void delete(Integer id) {
        if (id == null || id <= 1) {
            throw new RuntimeException("Wrong id");
        }
        wagonRepository.delete(id);
    }
}

