package com.example.p_flayawaytravel.services;

import com.example.p_flayawaytravel.domain.Booking;
import com.example.p_flayawaytravel.domain.Flight;
import com.example.p_flayawaytravel.domain.User;
import com.example.p_flayawaytravel.dto.RequestBookingDTO;
import com.example.p_flayawaytravel.dto.ResponseBookingDTO;
import com.example.p_flayawaytravel.repository.BookingRepository;
import com.example.p_flayawaytravel.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;

    public ResponseBookingDTO book(RequestBookingDTO dto, User currentUser) {
        Flight flight = flightRepository.findById(dto.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        if (flight.getAvailableSeats() <= 0) {
            throw new RuntimeException("No available seats on this flight");
        }

        LocalDateTime now = LocalDateTime.now();
        if (!flight.getEstDepartureTime().isAfter(now)) {
            throw new RuntimeException("Cannot book a flight that has already departed or is in transit");
        }

        boolean hasConflict = bookingRepository.hasScheduleConflict(
                currentUser,
                flight.getEstDepartureTime(),
                flight.getEstArrivalTime()
        );
        if (hasConflict) {
            throw new RuntimeException("You already have a booking that conflicts with this flight's schedule");
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightRepository.save(flight);


        Booking booking = new Booking();
        booking.setUser(currentUser);
        booking.setFlight(flight);
        booking.setBookingDate(now);

        Booking saved = bookingRepository.save(booking);

        saveConfirmationFile(saved);

        return mapToResponse(saved);
    }

    public ResponseBookingDTO findById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return mapToResponse(booking);
    }

    private ResponseBookingDTO mapToResponse(Booking booking) {
        ResponseBookingDTO response = new ResponseBookingDTO();
        response.setId(booking.getId());
        response.setUserId(booking.getUser().getId());
        response.setUserFirstName(booking.getUser().getFirstName());
        response.setUserLastName(booking.getUser().getLastName());
        response.setFlightId(booking.getFlight().getId());
        response.setFlightNumber(booking.getFlight().getFlightNumber());
        response.setEstDepartureTime(booking.getFlight().getEstDepartureTime());
        response.setEstArrivalTime(booking.getFlight().getEstArrivalTime());
        response.setBookingDate(booking.getBookingDate());
        return response;
    }

    private void saveConfirmationFile(Booking booking) {
        String filename = "flight_booking_email_" + booking.getId() + ".txt";
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("=== Confirmacion del Booking ===\n");
            writer.write("ID Booking: " + booking.getId() + "\n");
            writer.write("Pasajero: " + booking.getUser().getFirstName()
                    + " " + booking.getUser().getLastName() + "\n");
            writer.write("Flight N°: " + booking.getFlight().getFlightNumber() + "\n");
            writer.write("Departure: " + booking.getFlight().getEstDepartureTime() + "\n");
            writer.write("Arrival: " + booking.getFlight().getEstArrivalTime() + "\n");
            writer.write("Dia del Booking: " + booking.getBookingDate() + "\n");
        } catch (IOException e) {
            System.err.println("No se guardo el archivo: " + e.getMessage());
        }
    }
}
