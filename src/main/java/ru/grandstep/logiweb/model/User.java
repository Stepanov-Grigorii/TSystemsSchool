package ru.grandstep.logiweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @NotBlank(message = "field login can't be blank")
    private String login;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "Неккоректный пароль")
    private String password;
    @Pattern(regexp = "^(.+)@(.+)$", message = "Неккоректный email")
    private String email;

    @AllArgsConstructor
    @Getter
    public enum Role{
        DRIVER("Водитель"),
        ADMIN("Администратор");
        private final String name;
    }

    public abstract Role getRole();
}
