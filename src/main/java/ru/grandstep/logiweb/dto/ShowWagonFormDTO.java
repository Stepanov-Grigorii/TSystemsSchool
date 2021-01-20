package ru.grandstep.logiweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.grandstep.logiweb.model.Wagon;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ShowWagonFormDTO {
    private Integer id;

    private String registryNumber;
    private Integer driverNumber;
    private Integer capacity;
    private String brand;
    private Wagon.Status status;
    private List<String> statusNames;
    private Integer cityId;
    private List<CityDTO> cityDtoList;

    public ShowWagonFormDTO() {
        this.statusNames = Arrays.stream(Wagon.Status.values())
                                 .map(Wagon.Status::getName).collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    public static class CityDTO{

        Integer id;
        String name;
    }
}
