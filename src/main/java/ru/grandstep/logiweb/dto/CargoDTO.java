package ru.grandstep.logiweb.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class CargoDTO {
    private Integer id;
    @NotBlank(message = "Содержимое не должно быть пустым")
    private String name;
    private BigDecimal weight;
    private String status;
    private String departureName;
    private String destinationName;
    private String number;
}
