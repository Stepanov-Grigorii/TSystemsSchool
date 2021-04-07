package ru.grandstep.logiweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.exception.WrongIdException;
import ru.grandstep.logiweb.integration.ActivemqProducer;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.model.Order;
import ru.grandstep.logiweb.model.Wagon;
import ru.grandstep.logiweb.repository.OrderRepository;
import ru.grandstep.logiweb.repository.WagonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WagonService {
    private final WagonRepository wagonRepository;
    private final DriverService driverService;
    private final ActivemqProducer producer;
    private final CityService cityService;

    public Wagon getById(Integer id) throws WrongIdException, NotFoundException {
        if (id == null || id <= 0) {
            throw new WrongIdException();
        }
        return wagonRepository.getById(id);
    }

    public List<Wagon> getAll() {
        return wagonRepository.getAll();
    }

    public Wagon saveOrUpdate(Wagon wagon) throws NotFoundException {
        if (wagon.getCurrentCity().getName() == null) {
            wagon.setCurrentCity(cityService.getById(wagon.getCurrentCity().getId()));
        }

        Wagon savedWagon = wagonRepository.saveOrUpdate(wagon);
        producer.sendWagon(savedWagon);
        return savedWagon;
    }

    public void delete(Integer id) throws WrongIdException, NotFoundException {
        if (id == null || id <= 0) {
            throw new WrongIdException();
        }

        for (Driver driver : driverService.getAllDriversInWagon(id)) {
            driver.setWagon(null);
            driverService.saveOrUpdate(driver);
        }

//        for (Order order : orderService.getAllByWagonId(id)) {
//            order.setWagon(null);
//            orderService.saveOrUpdate(order);
//        }

        wagonRepository.delete(id);
    }
}

