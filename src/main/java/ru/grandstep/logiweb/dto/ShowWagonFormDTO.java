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
    private String status;
    private List<String> statusNames;
    private Integer cityId;
    private List<CityDTO> cityDtoList;
    private List<Integer> driverIds;
    private List<DriverDto> driverDtoList;

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

    @Data
    @AllArgsConstructor
    public static class DriverDto{
        Integer id;
        String identityNumber;
    }
}
