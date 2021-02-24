package ru.grandstep.logiweb.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.exception.WrongIdException;
import ru.grandstep.logiweb.filler.DriverID;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.repository.DriverRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;
    private final Logger rootLogger = LogManager.getRootLogger();
    private final Logger driverLogger = LogManager.getLogger(DriverService.class);

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
        if (driver.getId() != null) {
            Driver oldDriver = driverRepository.getById(driver.getId());
            driver.setIdentityNumber(oldDriver.getIdentityNumber());
        } else {
            driver.setIdentityNumber(DriverID.getUserId(driver.getName(), driver.getSurname(), driverRepository.getIdentityNumbers()));
        }
        driverLogger.info("Driver with id " + driver.getId() + " added");
        return driverRepository.saveOrUpdate(driver);
    }

    public Driver update(Driver driver) throws NotFoundException {
        if (driver.getId() == null) {
            driver.setIdentityNumber(DriverID.getUserId(driver.getName(), driver.getSurname(), driverRepository.getIdentityNumbers()));
        } else {
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

        Driver savedDriver = driverRepository.saveOrUpdate(driver);
        driverLogger.info("Driver with id " + savedDriver.getId() + " added");
        return savedDriver;
    }

    public List<Driver> getAllFreeDrivers() {
        return driverRepository.getAllFreeDrivers();
    }

    public List<Driver> getAllDriversInWagon(Integer id) {
        return driverRepository.getAllDriversInWagon(id);
    }

    public List<Driver> getAllDriversByOrder(Integer id){
        return driverRepository.getAllDriversByOrder(id);
    }

    public void delete(Integer id) throws WrongIdException {
        if (id == null || id < 1) {
            throw new WrongIdException();
        }
        driverRepository.delete(id);
    }
}
