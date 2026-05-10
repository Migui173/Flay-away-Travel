package com.example.p_flayawaytravel.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
public class RequestLoginDTO {
    @NotNull
    @NotEmpty
    @Email
    private String email;

    private String password;
}
