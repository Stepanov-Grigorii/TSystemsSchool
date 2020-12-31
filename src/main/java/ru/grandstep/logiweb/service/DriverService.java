package ru.grandstep.logiweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.repository.DriverRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;

    public Driver getById(Integer id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("Wrong id");
        }
        return driverRepository.getById(id);
    }

    public List<Driver> getAll() {
        return driverRepository.getAll();
    }

    public Driver saveOrUpdate(Driver driver) {
        return driverRepository.saveOrUpdate(driver);
    }

    public void delete(Integer id) {
        if (id == null || id <= 1) {
            throw new RuntimeException("Wrong id");
        }
        driverRepository.delete(id);
    }
}
