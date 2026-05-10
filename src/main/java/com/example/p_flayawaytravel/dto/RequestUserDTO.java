package com.example.p_flayawaytravel.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.*;

@Getter
@Setter
public class RequestUserDTO {
    @Email
    @NotNull
    @NotEmpty
    private String email;

    @NotEmpty
    @NotNull
    @Pattern(regexp = "^[A-Z].+")
    private String firstName;

    @NotEmpty
    @NotNull
    @Pattern(regexp = "^[A-Z].+")
    private String lastName;

    @NotEmpty
    @NotNull
    @Length(min = 8)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).+$", message = "La contraseña necesita tener al menos una l y un número")
    private String password;
}
