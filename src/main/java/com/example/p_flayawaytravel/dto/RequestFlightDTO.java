package com.example.p_flayawaytravel.dto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
public class RequestFlightDTO {
    @NotNull
    @NotEmpty
    private String airLineName;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Z]{2,3}[0-9]{3}$")
    private String flightNumber;

    @NotNull
    @NotEmpty
    private Date estDEpartureTime;

    @NotNull
    @NotEmpty
    private Date estArrivalTime;

    @NotNull
    @NotEmpty
    @Min(1)
    private Integer availableStats;
}
