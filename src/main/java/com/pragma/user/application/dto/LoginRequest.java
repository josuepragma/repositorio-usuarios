package com.pragma.user.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginRequest {

    @NotEmpty(message = "EMAIL must not be empty")
    private String email;

    @NotEmpty(message = "PASSWORD must not be empty")
    private String password;
}
