package ru.grandstep.logiweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.exception.WrongIdException;
import ru.grandstep.logiweb.filler.DriverID;
import ru.grandstep.logiweb.model.City;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.repository.DriverRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;

    public Driver getById(Integer id) throws WrongIdException, NotFoundException {
        if (id == null || id <= 0) {
            throw new WrongIdException();
        }
        return driverRepository.getById(id);
    }

    public List<Driver> getAll() {
        return driverRepository.getAll();
    }

    public Driver saveOrUpdate(Driver driver) throws NotFoundException {
        if(driver.getId() != null){
            Driver oldDriver = driverRepository.getById(driver.getId());
            //oldDriver.setName(driver.getName());
            //oldDriver.setSurname(driver.getSurname());
            //oldDriver.setWagon(driver.getWagon());
            //oldDriver.setLogin(driver.getLogin());
            //oldDriver.setHoursInCurrentMonth(driver.getHoursInCurrentMonth());

            driver.setIdentityNumber(oldDriver.getIdentityNumber());

            //driver = oldDriver;
        }
        else {
            driver.setIdentityNumber(DriverID.getUserId(driver.getName(), driver.getSurname(), driverRepository.getIdentityNumbers()));
        }
        return driverRepository.saveOrUpdate(driver);
    }

    public Driver update(Driver driver) throws NotFoundException {
        if(driver.getId() == null){
            driver.setIdentityNumber(DriverID.getUserId(driver.getName(), driver.getSurname(), driverRepository.getIdentityNumbers()));
        }else {
            Driver oldDriver = driverRepository.getById(driver.getId());
            oldDriver.setName(driver.getName());
            oldDriver.setSurname(driver.getSurname());
            oldDriver.setLogin(driver.getLogin());
            oldDriver.setHoursInCurrentMonth(driver.getHoursInCurrentMonth());
            if (driver.getPassword() != null) {
                oldDriver.setPassword(driver.getPassword());
            }
            driver = oldDriver;
        }
        return driverRepository.saveOrUpdate(driver);
    }

    public List<Driver> getAllFreeDrivers(){
        return driverRepository.getAllFreeDrivers();
    }

    public List<Driver> getAllDriversInWagon(Integer id){
        return driverRepository.getAllDriversInWagon(id);
    }

    public void delete(Integer id) throws WrongIdException {
        if (id == null || id < 1) {
            throw new WrongIdException();
        }
        driverRepository.delete(id);
    }
}
