package com.example.p_flayawaytravel.service;

import com.example.p_flayawaytravel.domain.Flight;
import com.example.p_flayawaytravel.dto.RequestFlightDTO;
import com.example.p_flayawaytravel.dto.ResponseFlightDTO;
import com.example.p_flayawaytravel.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    public ResponseFlightDTO create(RequestFlightDTO dto) {
        if (flightRepository.existsByFlightNumber(dto.getFlightNumber())) {
            throw new RuntimeException("Flight number already exists");
        }
        if (!dto.getEstDepartureTime().isBefore(dto.getEstArrivalTime())) {
            throw new RuntimeException("Departure time must be before arrival time");
        }

        Flight flight = modelMapper.map(dto, Flight.class);
        Flight saved = flightRepository.save(flight);
        return modelMapper.map(saved, ResponseFlightDTO.class);
    }

    public ResponseFlightDTO findById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        return modelMapper.map(flight, ResponseFlightDTO.class);
    }

    public List<ResponseFlightDTO> search(String flightNumber, String airlineName,
                                          LocalDateTime start, LocalDateTime end) {
        List<Flight> results;

        if (flightNumber != null && !flightNumber.isBlank()) {
            results = flightRepository.findByFlightNumberContainingIgnoreCase(flightNumber);
        } else if (airlineName != null && !airlineName.isBlank()) {
            results = flightRepository.findByAirlineNameContainingIgnoreCase(airlineName);
        } else if (start != null && end != null) {
            results = flightRepository.findByEstDepartureTimeBetween(start, end);
        } else {
            results = flightRepository.findAll();
        }

        return results.stream()
                .map(f -> modelMapper.map(f, ResponseFlightDTO.class))
                .toList();
    }
}