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
@Table(name = "orders")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String number;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "order")
    private List<Waypoint> waypointList;
    @ManyToOne
    private Wagon wagon;

    @AllArgsConstructor
    @Getter
    public enum Status{
        WAITING("Ожидание"),//TODO уточнить
        PROCESS("В процессе"),
        COMPLETED("Выполнен");
        private final String name;
    }
}
