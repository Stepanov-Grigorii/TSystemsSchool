package ru.grandstep.logiweb.mapper;

import org.springframework.stereotype.Component;
import ru.grandstep.logiweb.dto.DriverDTO;
import ru.grandstep.logiweb.model.Driver;

@Component
public class DriverMapper {

    public DriverDTO getDriverDTO(Driver driver){
        DriverDTO dto = new DriverDTO();

        dto.setId(driver.getId());
        dto.setName(driver.getName());
        dto.setSurname(driver.getSurname());
        dto.setEmail(driver.getEmail());
        dto.setIdentityNumber(driver.getIdentityNumber());
        dto.setHoursInCurrentMonth(driver.getHoursInCurrentMonth());
        //dto.setCity(driver.getCurrentCity().getName());
        //dto.setWagon(driver.getWagon().getRegistryNumber());
        //dto.setStatus(driver.getStatus().getName());

        return dto;
    }

    public Driver getDriver(DriverDTO dto){
        Driver driver = new Driver();
        driver.setId(dto.getId());
        driver.setName(dto.getName());
        driver.setSurname(dto.getSurname());
        driver.setEmail(dto.getEmail());
        return driver;
    }
}
