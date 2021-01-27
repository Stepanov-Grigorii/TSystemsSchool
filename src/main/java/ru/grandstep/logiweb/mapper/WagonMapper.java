package ru.grandstep.logiweb.mapper;

import org.springframework.stereotype.Component;
import ru.grandstep.logiweb.dto.ShowWagonFormDTO;
import ru.grandstep.logiweb.dto.WagonDTO;
import ru.grandstep.logiweb.model.City;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.model.Order;
import ru.grandstep.logiweb.model.Wagon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WagonMapper {

    public WagonDTO getWagonDTO(Wagon wagon) {
        WagonDTO dto = new WagonDTO();

        dto.setId(wagon.getId());
        dto.setRegistryNumber(wagon.getRegistryNumber());
        dto.setDriverNumber(wagon.getDriverNumber());
        dto.setCapacity(wagon.getCapacity());
        dto.setBrand(wagon.getBrand());
        dto.setStatus(wagon.getStatus().getName());
        dto.setCity(wagon.getCurrentCity().getName());

        return dto;
    }

    public List<WagonDTO> setOrderToWagonDTO(List<WagonDTO> wagonDTOList, List<Order> orderList) {
        for (Order order : orderList) {
            for (WagonDTO dto : wagonDTOList) {
                if (dto.getId().equals(order.getWagon().getId()) && order.getStatus() != Order.Status.COMPLETED) {
                    dto.setOrder(order.getNumber());
                }
            }
        }
        return wagonDTOList;
    }

    public List<WagonDTO> setDriversToWagonDTO(List<WagonDTO> wagonDTOList, List<Driver> driverList) {
        List<String> strings;
        for (WagonDTO dto : wagonDTOList) {
            strings = new ArrayList<>();
            for (Driver driver : driverList) {
                if (driver.getWagon() != null && driver.getWagon().getId().equals(dto.getId())) {
                    strings.add(driver.getIdentityNumber());
                }
            }
            dto.setDrivers(strings);
        }
        return wagonDTOList;
    }

    public Wagon getWagon(ShowWagonFormDTO dto) {
        Wagon wagon = new Wagon();
        City city = new City();

        wagon.setId(dto.getId());
        wagon.setBrand(dto.getBrand());
        wagon.setCapacity(dto.getCapacity());
        wagon.setRegistryNumber(dto.getRegistryNumber());
        wagon.setStatus(Wagon.Status.getStatusByName(dto.getStatus()));
        wagon.setDriverNumber(dto.getDriverNumber());
        city.setId(dto.getCityId());
        wagon.setCurrentCity(city);

        return wagon;
    }

    public ShowWagonFormDTO getShowWagonFormDTO(Integer id, Wagon wagon, List<City> cityList, List<Driver> driverList) {
        ShowWagonFormDTO dto = new ShowWagonFormDTO();

        dto.setId(id);
        dto.setDriverNumber(wagon.getDriverNumber());
        dto.setRegistryNumber(wagon.getRegistryNumber());
        dto.setCapacity(wagon.getCapacity());
        dto.setBrand(wagon.getBrand());
        if (wagon.getStatus() != null) {
            dto.setStatus(wagon.getStatus().getName());
        }
        dto.setCityDtoList(cityList.stream()
                .map(city -> new ShowWagonFormDTO.CityDTO(city.getId(), city.getName()))
                .collect(Collectors.toList()));
        dto.setDriverDtoList(driverList.stream()
                .map(driver -> new ShowWagonFormDTO.DriverDto(driver.getId(), driver.getIdentityNumber()))
                .collect(Collectors.toList()));

        return dto;
    }
}
