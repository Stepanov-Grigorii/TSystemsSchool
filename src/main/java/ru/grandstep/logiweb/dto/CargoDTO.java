package ru.grandstep.logiweb.dto;

import lombok.Data;

@Data
public class CargoDTO {
    private String name;
    private String weight;
    private String status;
    private String waypointName;
    private String number;
}
