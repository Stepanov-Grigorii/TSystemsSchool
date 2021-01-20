package ru.grandstep.logiweb.mapper;

import org.springframework.stereotype.Component;
import ru.grandstep.logiweb.dto.ShowWagonFormDTO;
import ru.grandstep.logiweb.dto.WagonDTO;
import ru.grandstep.logiweb.model.City;
import ru.grandstep.logiweb.model.Wagon;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WagonMapper {

    public WagonDTO getWagonDTO(Wagon wagon){
        WagonDTO dto = new WagonDTO();

        dto.setRegistryNumber(wagon.getRegistryNumber());
        dto.setDriverNumber(wagon.getDriverNumber());
        dto.setCapacity(wagon.getCapacity());
        dto.setBrand(wagon.getBrand());
        dto.setStatus(wagon.getStatus().getName());
        dto.setCity(wagon.getCurrentCity().getName());

        return dto;
    }

    public Wagon getWagon(ShowWagonFormDTO dto){
        Wagon wagon = new Wagon();
        City city = new City();

        wagon.setBrand(dto.getBrand());
        wagon.setCapacity(dto.getCapacity());
        wagon.setRegistryNumber(dto.getRegistryNumber());
        wagon.setStatus(dto.getStatus());
        city.setId(dto.getCityId());
        wagon.setCurrentCity(city);

        return wagon;
    }

    public ShowWagonFormDTO getShowWagonFormDTO(Integer id, Wagon wagon, List<City> cityList){
        ShowWagonFormDTO dto = new ShowWagonFormDTO();

        dto.setId(id);
        dto.setDriverNumber(wagon.getDriverNumber());
        dto.setCapacity(wagon.getCapacity());
        dto.setBrand(wagon.getBrand());
        dto.setStatus(wagon.getStatus());

        dto.setCityDtoList(cityList.stream()
                .map(city -> new ShowWagonFormDTO.CityDTO(city.getId(), city.getName()))
                .collect(Collectors.toList()));

        return dto;
    }
}
