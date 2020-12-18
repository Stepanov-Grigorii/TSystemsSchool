package ru.grandstep.logiweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Driver extends User{
    private String name;
    private String surname;
    private String identityNumber;
    private Integer hoursInCurrentMonth;
    @Enumerated(EnumType.STRING)  //записывать в бд имя самого enum
    private Status status;
    @ManyToOne
    private City currentCity;
    @ManyToOne
    private Wagon wagon;
    @AllArgsConstructor
    @Getter
    public enum Status{
        REST("Отдых"),
        WORK_SHIFT("В смене"),
        DRIVE("За рулем");

        private final String name;
    }

    @Override
    public Role getRole() {
        return Role.DRIVER;
    }
}
