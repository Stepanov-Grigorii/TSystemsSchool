package ru.grandstep.logiweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Wagon {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String registryNumber;
    private Integer driverNumber; // размер смены водителей
    private Integer capacity;
    private String brand;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private City currentCity;

    @AllArgsConstructor
    @Getter
    public enum Status{
        WORKED("Исправен"),
        BROKEN("Сломан");
        private final String name;

    }
}
