package ru.grandstep.logiweb.filler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.repository.DriverRepository;

import javax.annotation.PostConstruct;


@Component
@RequiredArgsConstructor
public class Filler {
    private final DriverRepository driverRepository;
    @PostConstruct
    private void fill(){
        for(int i = 0; i < 20; i++){
            Driver driver = new Driver();
            driver.setName(DriverNames.getFirstName());
            driver.setSurname(DriverNames.getLastName());
            driver.setIdentityNumber(DriverID.getUserId(driver.getName(), driver.getSurname(), driverRepository.getIds()));
            driverRepository.saveOrUpdate(driver);
        }
    }
}