package com.pragma.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    Integer id;
    String name;
    String surname;
    String documentNumber;
    String phone;
    Date birthdate;
    String email;
    String password;
    Role role;
}
