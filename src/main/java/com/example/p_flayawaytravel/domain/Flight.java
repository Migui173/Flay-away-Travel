package com.example.p_flayawaytravel.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airLineName;
    private String flightNumber;
    private Date estDEpartureTime;
    private Date estArrivalTime;
    private Integer availableStats;
}
