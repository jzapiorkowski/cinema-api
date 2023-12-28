package com.cinema.cinema.reservation.repository;

import com.cinema.cinema.reservation.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r WHERE r.user.id = :userId")
    List<Reservation> findAllUsersReservations(@Param("userId") Integer userId);
}
