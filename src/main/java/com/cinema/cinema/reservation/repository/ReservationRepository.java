package com.cinema.cinema.reservation.repository;

import com.cinema.cinema.reservation.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r WHERE r.user.id = :userId")
    List<Reservation> findAllUsersReservations(@Param("userId") Integer userId);

    @Query("SELECT r FROM Reservation r WHERE EXTRACT(MONTH FROM r.createdAt) = :month")
    List<Reservation> findByMonth(@Param("month") Integer month);

    @Query("SELECT r FROM Reservation r WHERE EXTRACT(MONTH FROM r.createdAt) = :month AND EXTRACT(DAY FROM r.createdAt) = :day")
    List<Reservation> findByMonthAndDay(@Param("month") Integer month, @Param("day") Integer day);
}
