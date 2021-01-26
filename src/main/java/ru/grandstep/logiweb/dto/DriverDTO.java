package ru.grandstep.logiweb.dto;

import lombok.Data;
import ru.grandstep.logiweb.model.Order;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class DriverDTO {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank(message = "Фамилия не должна быть пустой")
    private String surname;
    private String login;
    private String password;
    private String email;
    private String identityNumber;
    private Integer hoursInCurrentMonth;
    private String city;
    private String wagon;
    private String status;
    private List<String> orderNumbers;
    private List<Order> orders;
    private List<String> driverIdentityNumbers;
    private String orderStatus;
}
