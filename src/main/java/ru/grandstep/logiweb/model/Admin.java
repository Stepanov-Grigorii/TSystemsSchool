package ru.grandstep.logiweb.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Admin extends User{
    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
