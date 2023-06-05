package com.pragma.user.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OwnerResponseDto {
    private Integer id;
    private String name;
    private String surname;
    private String documentNumber;
    private String phone;
    private String birthdate;
    private String email;
    private String password;
    private String role;
}
