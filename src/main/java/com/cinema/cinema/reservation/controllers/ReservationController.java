package com.cinema.cinema.reservation.controllers;

import com.cinema.cinema.reservation.dto.*;
import com.cinema.cinema.reservation.services.ReservationService;
import com.cinema.cinema.reservation.services.ReservationsStatisticsService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationsStatisticsService reservationsStatisticsService;

    public ReservationController(ReservationService reservationService, ReservationsStatisticsService reservationsStatisticsService) {
        this.reservationService = reservationService;
        this.reservationsStatisticsService = reservationsStatisticsService;
    }

    @Validated
    @PostMapping
    public ResponseEntity<ReservationOutputDto> createNewReservation(
             @RequestBody() CreateReservationInputDto createReservationInputDto
    ) throws BadRequestException {
        ReservationOutputDto reservation = reservationService.createNewReservation(createReservationInputDto);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @Validated
    @PutMapping("{id}")
    public ResponseEntity<ReservationOutputDto> updateReservation(
            @Valid @RequestBody UpdateReservationInputDto updateReservationInputDto,
            @PathVariable Integer id
    ) throws BadRequestException {
        ReservationOutputDto reservation = reservationService.updateReservation(id, updateReservationInputDto);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationOutputDto> reservationDetails(@PathVariable Integer id) {
        ReservationOutputDto reservation = reservationService.getReservationById(id);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReservationOutputDto>> reservationsList() {
        List<ReservationOutputDto> reservations = reservationService.getAllUserReservations();

        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("admin")
    public ResponseEntity<List<ReservationOutputDto>> reservationsListAdmin() {
        List<ReservationOutputDto> reservations = reservationService.getAllReservations();

        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteReservation(@PathVariable("id") Integer reservationId) {
        reservationService.deleteReservation(reservationId);
    }

    @Validated
    @GetMapping("/stats/by-time")
    public ResponseEntity<ReservationsStatisticsOutputDto> getStatisticsByTime(
            @RequestParam(required = false) @Nullable @Min(1) @Max(12) Integer month,
            @RequestParam(required = false) @Nullable @Min(1) @Max(31) Integer day
    ) {
        ReservationsStatisticsQueryInputDto reservationsStatisticsQueryInputDto = new ReservationsStatisticsQueryInputDto(month, day);
        ReservationsStatisticsOutputDto reservationStats = reservationsStatisticsService.getReservationsStats(reservationsStatisticsQueryInputDto);

        return new ResponseEntity<>(reservationStats, HttpStatus.OK);
    }

    @GetMapping("/stats/per-user")
    public ResponseEntity<List<ReservationsPerUserStatisticsOutputDto>> getReservationsPerUser() {
        List<ReservationsPerUserStatisticsOutputDto> reservationsPerUser =
                reservationsStatisticsService.getReservationsPerUser();

        return new ResponseEntity<>(reservationsPerUser, HttpStatus.OK);
    }
}
