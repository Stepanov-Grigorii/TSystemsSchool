package ru.grandstep.logiweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Waypoint {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @ManyToOne
    private City city;
    @ManyToOne
    private Cargo cargo;
    @Enumerated(EnumType.STRING)
    private Type type;
    @ManyToOne
    private Order order;

    @AllArgsConstructor
    @Getter
    public enum Type{
        LOADING("Погрузка"),
        UNLOADING("Выгрузка");
        private final String name;
    }
}
