package com.example.p_flayawaytravel.controller;

import com.example.p_flayawaytravel.dto.RequestFlightDTO;
import com.example.p_flayawaytravel.dto.ResponseFlightDTO;
import com.example.p_flayawaytravel.services.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping("/create")
    public ResponseEntity<ResponseFlightDTO> create(@Valid @RequestBody RequestFlightDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFlightDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ResponseFlightDTO>> search(
            @RequestParam(required = false) String flightNumber,
            @RequestParam(required = false) String airlineName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(flightService.search(flightNumber, airlineName, start, end));
    }
}