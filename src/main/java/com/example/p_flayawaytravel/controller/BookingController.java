package com.example.p_flayawaytravel.controller;

import com.example.p_flayawaytravel.domain.User;
import com.example.p_flayawaytravel.dto.RequestBookingDTO;
import com.example.p_flayawaytravel.dto.ResponseBookingDTO;
import com.example.p_flayawaytravel.services.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/flights/book")
    public ResponseEntity<ResponseBookingDTO> book(
            @Valid @RequestBody RequestBookingDTO dto,
            @AuthenticationPrincipal User currentUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.book(dto, currentUser));
    }

    @GetMapping("/flight/book/{id}")
    public ResponseEntity<ResponseBookingDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.findById(id));
    }
}