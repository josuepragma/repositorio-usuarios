package com.pragma.user.application.dto.request;

import com.pragma.user.application.validations.BirthDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class OwnerRequestDto {

    @NotEmpty(message = "NAME field is required.")
    private String name;

    @NotEmpty(message = "SURNAME field is required.")
    private String surname;

    @NotEmpty(message = "DOCUMENT NUMBER field is required.")
    @Pattern(regexp = "^\\d*$", message = "DOCUMENT NUMBER field must be only numbers")
    private String documentNumber;

    @NotEmpty(message = "PHONE field is required.")
    @Pattern(regexp = "^\\+?\\d*$", message = "Invalid PHONE format")
    @Size(max = 13, message = "PHONE field must be have 13 digits maximum")
    private String phone;

    @NotNull(message = "DATE OF BIRTH is required.")
    @BirthDate(message = "DATE OF BIRTH must be greater than 18")
    @Past(message = "DATE OF BIRTH must be in the past.")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @NotEmpty(message = "EMAIL field is required.")
    @Email(message = "EMAIL address is invalid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;

    @NotEmpty(message = "PASSWORD field is required.")
    private String password;
}
