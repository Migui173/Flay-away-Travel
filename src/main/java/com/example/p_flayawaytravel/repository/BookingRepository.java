package com.example.p_flayawaytravel.repository;

import com.example.p_flayawaytravel.domain.Booking;
import com.example.p_flayawaytravel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    @Query("""
        SELECT COUNT(b) > 0 FROM Booking b
        WHERE b.user = :user
        AND b.flight.estDepartureTime < :arrivalTime
        AND b.flight.estArrivalTime > :departureTime
    """)
    boolean hasScheduleConflict(
            @Param("user") User user,
            @Param("departureTime") LocalDateTime departureTime,
            @Param("arrivalTime") LocalDateTime arrivalTime
    );
}
