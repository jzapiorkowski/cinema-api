package com.cinema.cinema.reservation.services;

import com.cinema.cinema.reservation.dto.CreateReservationInputDto;
import com.cinema.cinema.reservation.dto.NewReservationOutputDto;
import com.cinema.cinema.reservation.exceptions.ReservationNotFoundException;
import com.cinema.cinema.reservation.models.Reservation;
import com.cinema.cinema.reservation.repository.ReservationRepository;
import com.cinema.cinema.screening.dto.ScreeningOutputDto;
import com.cinema.cinema.screening.models.Screening;
import com.cinema.cinema.screening.services.ScreeningService;
import com.cinema.cinema.seat.dto.SeatOutputDto;
import com.cinema.cinema.seat.models.Seat;
import com.cinema.cinema.seat.services.SeatService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ScreeningService screeningService;
    private final SeatService seatService;

    public ReservationService(ReservationRepository reservationRepository, ScreeningService screeningService, SeatService seatService) {
        this.reservationRepository = reservationRepository;
        this.screeningService = screeningService;
        this.seatService = seatService;
    }

    public NewReservationOutputDto getNewReservationData(List<Integer> seats, Integer screeningId) {
        List<SeatOutputDto> chosenSeats = seatService.getSeatsByIds(seats);
        ScreeningOutputDto screening = screeningService.getScreeningById(screeningId);

        NewReservationOutputDto newReservation = new NewReservationOutputDto();
        newReservation.setSeats(chosenSeats);
        newReservation.setScreening(screening);

        return newReservation;
    }

    public Reservation createNewReservation(CreateReservationInputDto createReservationInputDto) {
        Screening screening = screeningService.getScreeningEntityById(createReservationInputDto.getScreening());
        List<Seat> seats = seatService.getSeatEntitiesByIds(createReservationInputDto.getSeats());

        Reservation reservation = new Reservation();
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setScreening(screening);
        reservation.setSeats(seats);

        return reservationRepository.save(reservation);
    }

    public Reservation getReservationById(Integer reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(() -> new ReservationNotFoundException(reservationId));
    }
}
