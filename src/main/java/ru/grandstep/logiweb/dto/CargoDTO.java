package ru.grandstep.logiweb.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CargoDTO {
    private Integer id;
    private String name;
    private BigDecimal weight;
    private String status;
    private String departureName;
    private String destinationName;
    private String number;
}
