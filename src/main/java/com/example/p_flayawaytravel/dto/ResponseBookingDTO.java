package com.example.p_flayawaytravel.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseBookingDTO {

    private Long id;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private Long flightId;
    private String flightNumber;
    private LocalDateTime estDepartureTime;
    private LocalDateTime estArrivalTime;
    private LocalDateTime bookingDate;
}
