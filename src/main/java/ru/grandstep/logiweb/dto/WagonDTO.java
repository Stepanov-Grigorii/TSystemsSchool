package ru.grandstep.logiweb.dto;

import lombok.Data;

@Data
public class WagonDTO {
    private Integer id;
    private String registryNumber;
    private Integer driverNumber;
    private Integer capacity;
    private String brand;
    private String status;
    private String city;
}
