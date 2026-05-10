package com.example.p_flayawaytravel.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ResponseUserDTO {
    private String username;

    @Email
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
    private String password;
}
