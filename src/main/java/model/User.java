package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Класс POJO для пользователя
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private String name;
    private String email;
    private String password;
}
