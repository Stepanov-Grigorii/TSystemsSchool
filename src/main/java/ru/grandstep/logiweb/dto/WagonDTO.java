package ru.grandstep.logiweb.dto;

import lombok.Data;

import java.util.List;

@Data
public class WagonDTO {
    private Integer id;
    private String registryNumber;
    private Integer driverNumber;
    private Integer capacity;
    private String brand;
    private String status;
    private String city;
    private String order;
    private List<String> drivers;
}
