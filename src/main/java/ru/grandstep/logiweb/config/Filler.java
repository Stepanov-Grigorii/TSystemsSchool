package ru.grandstep.logiweb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.repository.DriverRepository;

import javax.annotation.PostConstruct;
import java.util.List;


@Component
@RequiredArgsConstructor
public class Filler {
    private final DriverRepository driverRepository;
    @PostConstruct
    private void fill(){
        driverRepository.saveOrUpdate(new Driver());
        List<Driver> drivers = driverRepository.getAll();
        System.out.println(drivers);
    }
}
