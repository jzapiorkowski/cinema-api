package com.cinema.cinema.reservation.services;

import com.cinema.cinema.movie.models.Movie;
import com.cinema.cinema.reservation.dto.ReservationsStatisticsOutputDto;
import com.cinema.cinema.reservation.dto.ReservationsStatisticsQueryInputDto;
import com.cinema.cinema.reservation.exceptions.UnauthorizedUserException;
import com.cinema.cinema.reservation.models.Reservation;
import com.cinema.cinema.reservation.repository.ReservationRepository;
import com.cinema.cinema.screening.models.Screening;
import com.cinema.cinema.seat.models.Seat;
import com.cinema.cinema.user.services.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReservationsStatisticsService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;

    public ReservationsStatisticsService(ReservationRepository reservationRepository, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
    }

public ReservationsStatisticsOutputDto getReservationsStats(
        ReservationsStatisticsQueryInputDto reservationsStatisticsQueryInputDto) {
    if (!userService.doesUserHavePermission(new String[]{"ADMIN"})) {
        throw new UnauthorizedUserException();
    }

    if (reservationsStatisticsQueryInputDto.getMonth() != null && reservationsStatisticsQueryInputDto.getDay() == null) {
        return getStatisticsForMonth(reservationsStatisticsQueryInputDto.getMonth());
    } else if (reservationsStatisticsQueryInputDto.getMonth() != null) {
        return getStatisticsForDay(reservationsStatisticsQueryInputDto.getMonth(), reservationsStatisticsQueryInputDto.getDay());
    } else {
        return getAllReservationsStatistics();
    }
}

    private ReservationsStatisticsOutputDto getStatisticsForMonth(Integer month) {
        List<Reservation> reservations = reservationRepository.findByMonth(month);

        ReservationsStatisticsOutputDto stats = new ReservationsStatisticsOutputDto();
        populateStatistics(stats, reservations);

        return stats;
    }

    private ReservationsStatisticsOutputDto getStatisticsForDay(Integer month, Integer day) {
        List<Reservation> reservations = reservationRepository.findByMonthAndDay(month, day);

        ReservationsStatisticsOutputDto stats = new ReservationsStatisticsOutputDto();
        populateStatistics(stats, reservations);

        return stats;
    }

    private ReservationsStatisticsOutputDto getAllReservationsStatistics() {
        List<Reservation> reservations = reservationRepository.findAll();

        ReservationsStatisticsOutputDto stats = new ReservationsStatisticsOutputDto();
        populateStatistics(stats, reservations);

        return stats;
    }

    private void populateStatistics(ReservationsStatisticsOutputDto stats, List<Reservation> reservations) {
        stats.setReservedSeats(countSeats(reservations));
        stats.setNumberOfMovies(countMovies(reservations));
        stats.setReservationsNumber(reservations.size());
    }

    private int countSeats(List<Reservation> reservations) {
        int totalSeats = 0;

        for (Reservation reservation : reservations) {
            List<Seat> seats = reservation.getSeats();
            if (seats != null) {
                totalSeats += seats.size();
            }
        }

        return totalSeats;
    }

    private int countMovies(List<Reservation> reservations) {
        Set<Movie> uniqueMovies = new HashSet<>();

        for (Reservation reservation : reservations) {
            Screening screening = reservation.getScreening();
            if (screening != null) {
                Movie movie = screening.getMovie();
                if (movie != null) {
                    uniqueMovies.add(movie);
                }
            }
        }

        return uniqueMovies.size();
    }
}
