package ru.grandstep.logiweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.grandstep.logiweb.dto.OrderDTO;

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
    @OneToOne
    private Action actionDeparture;
    @OneToOne
    private Action actionDestination;
    @ManyToOne
    private Wagon wagon;

    @AllArgsConstructor
    @Getter
    public enum Status{
        WAITING("Ожидание"),
        PROCESS("В процессе"),
        COMPLETED("Выполнен");
        private final String name;

        public static Status getStatusByName(String name){
            return switch (name){
                case "Ожидание" -> WAITING;
                case "В процессе" -> PROCESS;
                case "Выполнен" -> COMPLETED;
                default -> throw new IllegalStateException("Unexpected value: " + name);
            };
        }
    }
}
