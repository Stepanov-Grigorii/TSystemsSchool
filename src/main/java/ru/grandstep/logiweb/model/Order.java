package ru.grandstep.logiweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String number;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany
    private List<Waypoint> waypointList;
    @ManyToOne
    private Wagon wagon;
    @OneToMany
    private List<Driver> drivers;

    @AllArgsConstructor
    @Getter
    public enum Status{
        COMPLETED("Выполнен"),
        PROCESS("В процессе");
        private final String name;
    }
}
