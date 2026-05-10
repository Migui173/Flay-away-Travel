package com.example.p_flayawaytravel.controller;

import com.example.p_flayawaytravel.repository.BookingRepository;
import com.example.p_flayawaytravel.repository.FlightRepository;
import com.example.p_flayawaytravel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cleanup")
@RequiredArgsConstructor
public class CleanupController {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    @DeleteMapping
    public ResponseEntity<Void> cleanup() {
        bookingRepository.deleteAll();
        flightRepository.deleteAll();
        userRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}