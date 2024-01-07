package com.cinema.cinema.reservation.services;

import com.cinema.cinema.reservation.dto.*;
import com.cinema.cinema.reservation.exceptions.ReservationNotFoundException;
import com.cinema.cinema.reservation.exceptions.UnauthorizedUserException;
import com.cinema.cinema.reservation.mappers.ReservationMapper;
import com.cinema.cinema.reservation.models.Reservation;
import com.cinema.cinema.reservation.repository.ReservationRepository;
import com.cinema.cinema.screening.dto.ScreeningOutputDto;
import com.cinema.cinema.screening.models.Screening;
import com.cinema.cinema.screening.services.ScreeningService;
import com.cinema.cinema.seat.dto.SeatOutputDto;
import com.cinema.cinema.seat.models.Seat;
import com.cinema.cinema.seat.services.SeatService;
import com.cinema.cinema.user.models.User;
import com.cinema.cinema.user.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ScreeningService screeningService;
    private final SeatService seatService;
    private final UserService userService;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, ScreeningService screeningService, SeatService seatService, UserService userService, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.screeningService = screeningService;
        this.seatService = seatService;
        this.userService = userService;
        this.reservationMapper = reservationMapper;
    }

    public NewReservationOutputDto getNewReservationData(List<Integer> seats, Integer screeningId) {
        List<SeatOutputDto> chosenSeats = seatService.getSeatsByIds(seats);
        ScreeningOutputDto screening = screeningService.getScreeningById(screeningId);

        NewReservationOutputDto newReservation = new NewReservationOutputDto();
        newReservation.setSeats(chosenSeats);
        newReservation.setScreening(screening);

        return newReservation;
    }

    public ReservationOutputDto createNewReservation(CreateReservationInputDto createReservationInputDto) throws BadRequestException {
        if (!seatService.checkIfSeatsAreAvailable(createReservationInputDto.getScreening(), createReservationInputDto.getSeats())) {
            throw new BadRequestException();
        }

        Screening screening = screeningService.getScreeningEntityById(createReservationInputDto.getScreening());
        List<Seat> seats = seatService.getSeatEntitiesByIds(createReservationInputDto.getSeats());

        Reservation reservation = new Reservation();
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setScreening(screening);
        reservation.setSeats(seats);
        reservation.setUser(userService.getCurrentUserEntity());

        Reservation createdReservation = reservationRepository.save(reservation);

        return reservationMapper.reservationToReservationOutputDto(createdReservation);
    }

    public ReservationOutputDto getReservationById(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));

        ReservationOutputDto reservationOutputDto = reservationMapper.reservationToReservationOutputDto(reservation);

        if (userService.doesUserHavePermission(new String[]{"ADMIN"})) {
            return reservationOutputDto;
        }

        Integer currentUserId = userService.getCurrentUserEntity().getId();

        if (!currentUserId.equals(reservation.getUser().getId())) {
            throw new UnauthorizedUserException();
        }

        return reservationOutputDto;
    }

    public List<ReservationOutputDto> getAllUserReservations() {
        User currentUser = userService.getCurrentUserEntity();
        List<Reservation> reservations = reservationRepository.findAllUsersReservations(currentUser.getId());

        return reservationMapper.reservationsToReservationOutputDtos(reservations);
    }

    public List<ReservationOutputDto> getAllUserReservations(Integer userId) {
        List<Reservation> reservations = reservationRepository.findAllUsersReservations(userId);

        return reservationMapper.reservationsToReservationOutputDtos(reservations);
    }

    public List<ReservationOutputDto> getAllReservations() {
        if (!userService.doesUserHavePermission(new String[]{"ADMIN"})) {
            throw new UnauthorizedUserException();
        }

        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.reservationsToReservationOutputDtos(reservations);
    }

    public void deleteReservation(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));

        if (reservation.getUser().getId().equals(userService.getCurrentUserEntity().getId())
                || userService.doesUserHavePermission(new String[]{"ADMIN"})
        ) {
            reservationRepository.deleteById(reservationId);
            return;
        }

        throw new UnauthorizedUserException();
    }
}
