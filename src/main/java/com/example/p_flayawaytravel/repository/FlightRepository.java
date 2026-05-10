package com.example.p_flayawaytravel.repository;

import com.example.p_flayawaytravel.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    boolean existsByFlightNumber(String flightNumber);

    List<Flight> findByFlightNumberContainingIgnoreCase(String flightNumber);

    List<Flight> findByAirlineNameContainingIgnoreCase(String airlineName);

    List<Flight> findByEstDepartureTimeBetween(LocalDateTime start, LocalDateTime end);

    Optional<Flight> findByFlightNumber(String flightNumber);
}
