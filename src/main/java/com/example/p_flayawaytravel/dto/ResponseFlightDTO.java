package com.example.p_flayawaytravel.dto;
import lombok.*;
import java.time.*;
@Getter
@Setter

public class ResponseFlightDTO {

    private Long id;
    private String airlineName;
    private String flightNumber;
    private LocalDateTime estDepartureTime;
    private LocalDateTime estArrivalTime;
    private Integer availableSeats;
}
