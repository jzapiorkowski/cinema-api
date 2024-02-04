package com.cinema.cinema.reservation.repository;

import com.cinema.cinema.reservation.dto.ReservationsPerUserStatisticsOutputDto;
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

    @Query("SELECT new com.cinema.cinema.reservation.dto.ReservationsPerUserStatisticsOutputDto(u.id, u.firstName, u.lastName, COUNT(r.id))" +
            "FROM User u " +
            "LEFT JOIN u.reservations r " +
            "GROUP BY u.id, u.firstName, u.lastName")
    List<ReservationsPerUserStatisticsOutputDto> getReservationsPerUserStatistics();
}
