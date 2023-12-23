package com.cinema.cinema.reservation.repository;

import com.cinema.cinema.reservation.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
