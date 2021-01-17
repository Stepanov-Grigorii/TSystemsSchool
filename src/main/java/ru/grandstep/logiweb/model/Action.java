package ru.grandstep.logiweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Action {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @ManyToOne
    private Cargo cargo;
    @ManyToOne
    private Waypoint waypoint;
    @Enumerated(EnumType.STRING)
    private Type type;

    @AllArgsConstructor
    @Getter
    public enum Type{
        LOADING("Погрузка"),
        UNLOADING("Выгрузка");
        private final String name;
    }
}
