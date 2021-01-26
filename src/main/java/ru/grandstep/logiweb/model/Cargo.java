package ru.grandstep.logiweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Cargo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String number;
    private String name;
    private BigDecimal weight;
    @Enumerated(EnumType.STRING)
    private Status status;

    @AllArgsConstructor
    @Getter
    public enum Status{
        PREPARED("Подгтовлен"),
        SHIPPED("Отгружен"),
        DELIVERED("Доставлен");
        private final String name;
    }
}
