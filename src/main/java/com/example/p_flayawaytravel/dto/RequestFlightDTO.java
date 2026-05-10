package com.example.p_flayawaytravel.dto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.*;

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
    private LocalDateTime estDepartureTime;

    @NotNull
    @NotEmpty
    private LocalDateTime estArrivalTime;;

    @NotNull
    @NotEmpty
    @Min(1)
    private Integer availableSeats;
}
