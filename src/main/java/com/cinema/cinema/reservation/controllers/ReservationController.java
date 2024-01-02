package com.cinema.cinema.reservation.controllers;

import com.cinema.cinema.reservation.dto.*;
import com.cinema.cinema.reservation.models.Reservation;
import com.cinema.cinema.reservation.services.ReservationService;
import com.cinema.cinema.reservation.services.ReservationsStatisticsService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationsStatisticsService reservationsStatisticsService;

    public ReservationController(ReservationService reservationService, ReservationsStatisticsService reservationsStatisticsService) {
        this.reservationService = reservationService;
        this.reservationsStatisticsService = reservationsStatisticsService;
    }

    @GetMapping("new")
    public String newReservation(@RequestParam @NotNull Integer screeningId, Model model ) {
        model.addAttribute("screeningId", screeningId);

        return "new-reservation";
    }

    @PostMapping()
    public String confirmReservation(@Valid CreateReservationInputDto createReservationInputDto) throws BadRequestException {
        Reservation reservation = reservationService.createNewReservation(createReservationInputDto);

        return "redirect:/reservations/" + reservation.getId();
    }

    @Validated
    @GetMapping("summary")
    public String reservationSummary(@ModelAttribute("reservationData") CreateReservationInputDto reservationData, Model model) {
        NewReservationOutputDto newReservationInfo = reservationService.getNewReservationData(reservationData.getSeats(), reservationData.getScreening());

        model.addAttribute("seats", newReservationInfo.getSeats());
        model.addAttribute("screening", newReservationInfo.getScreening());
        model.addAttribute("newReservation", reservationData);

        return "reservation-summary";
    }

    @GetMapping("{id}")
    public String reservationDetails(@PathVariable Integer id, Model model) {
        ReservationOutputDto reservation = reservationService.getReservationById(id);

        model.addAttribute("reservation", reservation);

        return "reservation-details";
    }

    @GetMapping
    public String reservationsList(Model model) {
        List<ReservationOutputDto> reservations = reservationService.getAllUserReservations();

        model.addAttribute("reservations", reservations);

        return "reservations-list";
    }

    @GetMapping("admin")
    public String reservationsListAdmin(Model model) {
        List<ReservationOutputDto> reservations = reservationService.getAllReservations();

        model.addAttribute("reservations", reservations);

        return "reservations-list";
    }

    @PostMapping("delete/{id}")
    public String deleteReservation(@PathVariable("id") Integer reservationId) {
        reservationService.deleteReservation(reservationId);

        return "redirect:/reservations";
    }

    @Validated
    @GetMapping("/admin/stats")
    public String getAdminStatistics(
            Model model,
            @RequestParam(required = false) @Nullable @Min(1) @Max(12) Integer month,
            @RequestParam(required = false) @Nullable @Min(1) @Max(31) Integer day
    ) {
        ReservationsStatisticsQueryInputDto reservationsStatisticsQueryInputDto = new ReservationsStatisticsQueryInputDto(month, day);
        ReservationsStatisticsOutputDto reservationStats = reservationsStatisticsService.getReservationsStats(reservationsStatisticsQueryInputDto);

        model.addAttribute("statistics", reservationStats);

        return "reservations-statistics";
    }
}
