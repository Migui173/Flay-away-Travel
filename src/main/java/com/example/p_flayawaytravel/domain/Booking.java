package com.example.p_flayawaytravel.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    private LocalDateTime bookingDate;
}