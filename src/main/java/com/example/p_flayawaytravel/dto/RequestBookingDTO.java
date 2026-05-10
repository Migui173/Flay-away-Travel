package com.example.p_flayawaytravel.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class RequestBookingDTO {
    @NotNull
    private Long flightId;
}
