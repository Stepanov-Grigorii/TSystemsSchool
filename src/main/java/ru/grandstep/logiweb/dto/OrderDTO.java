package ru.grandstep.logiweb.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Integer wagonId;
    private String number;
    private String status;
    private String cargo;
    private String departure;
    private String destination;
    private String wagonRegistryNumber;
}
