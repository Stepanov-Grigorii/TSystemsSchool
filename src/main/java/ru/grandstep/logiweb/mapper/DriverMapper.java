package ru.grandstep.logiweb.mapper;

import org.springframework.stereotype.Component;
import ru.grandstep.logiweb.dto.DriverDTO;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.model.Order;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DriverMapper {

    public DriverDTO getDriverDTO(Driver driver) {
        DriverDTO dto = new DriverDTO();

        dto.setId(driver.getId());
        dto.setName(driver.getName());
        dto.setSurname(driver.getSurname());
        dto.setLogin(driver.getLogin());
        dto.setEmail(driver.getEmail());
        dto.setIdentityNumber(driver.getIdentityNumber());
        dto.setHoursInCurrentMonth(driver.getHoursInCurrentMonth());
        //dto.setCity(driver.getCurrentCity().getName());
        //dto.setWagon(driver.getWagon().getRegistryNumber());
        //dto.setStatus(driver.getStatus().getName());

        return dto;
    }

    public DriverDTO getDriverDTOForInfo(Driver driver, List<Order> orders, List<Driver> drivers) {
        DriverDTO dto = new DriverDTO();

        dto.setId(driver.getId());
        dto.setName(driver.getName());
        dto.setSurname(driver.getSurname());
        dto.setLogin(driver.getLogin());
        dto.setEmail(driver.getEmail());
        dto.setIdentityNumber(driver.getIdentityNumber());
        dto.setHoursInCurrentMonth(driver.getHoursInCurrentMonth());
        if (driver.getWagon() != null) {
            dto.setWagon(driver.getWagon().getRegistryNumber());
            dto.setCity(driver.getWagon().getCurrentCity().getName());
            dto.setOrders(orders);
            drivers.removeIf(d -> d.getIdentityNumber().equals(driver.getIdentityNumber()));
            dto.setDriverIdentityNumbers(drivers.stream().map(Driver::getIdentityNumber).collect(Collectors.toList()));
        }
        dto.setOrderNumbers(orders.stream().map(Order::getNumber).collect(Collectors.toList()));
        //dto.setCity(driver.getCurrentCity().getName());
        //dto.setWagon(driver.getWagon().getRegistryNumber());
        //dto.setStatus(driver.getStatus().getName());

        return dto;
    }

    public Driver getDriver(DriverDTO dto) {
        Driver driver = new Driver();
        driver.setId(dto.getId());
        driver.setName(dto.getName());
        driver.setSurname(dto.getSurname());
        driver.setLogin(dto.getLogin());
        driver.setPassword(dto.getPassword());
        driver.setEmail(dto.getEmail());
        return driver;
    }
}
