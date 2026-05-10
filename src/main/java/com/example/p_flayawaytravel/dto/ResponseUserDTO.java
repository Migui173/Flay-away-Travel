package com.example.p_flayawaytravel.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ResponseUserDTO {
    @Email
    @NotNull
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

    @NotEmpty
    @NotNull
    private String role;
}
