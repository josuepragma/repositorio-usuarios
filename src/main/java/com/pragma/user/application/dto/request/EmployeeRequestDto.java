package com.pragma.user.application.dto.request;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;

@Getter
@Setter
public class EmployeeRequestDto {

    @NotEmpty(message = "EMPLOYEE NAME field is required.")
    private String name;

    @NotEmpty(message = "EMPLOYEE SURNAME field is required.")
    private String surname;

    @NotEmpty(message = "DOCUMENT NUMBER field is required.")
    @Pattern(regexp = "^\\d*$", message = "DOCUMENT NUMBER field must be only numbers")
    private String documentNumber;

    @NotEmpty(message = "PHONE field is required.")
    @Pattern(regexp = "^\\+?\\d*$", message = "Invalid PHONE format")
    @Size(max = 13, message = "PHONE field must be have 13 digits maximum")
    private String phone;

    @NotEmpty(message = "EMAIL field is required.")
    @Email(message = "EMAIL address is invalid.", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String email;

    @NotEmpty(message = "PASSWORD field is required.")
    private String password;

}
