package ru.grandstep.logiweb.dto;

import lombok.Data;

@Data
public class DriverDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String identityNumber;
    private Integer hoursInCurrentMonth;
    private String city;
    private String wagon;
    private String status;
}
