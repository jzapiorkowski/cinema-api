package com.cinema.cinema.seat.repository;

import com.cinema.cinema.seat.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    @Query("SELECT s FROM Seat s " +
            "WHERE s NOT IN (SELECT r.seats FROM Reservation r WHERE r.screening.id = :screeningId)")
    List<Seat> findAvailableSeatsByScreeningId(@Param("screeningId") Integer screeningId);
}
