package com.cinema.cinema.reservation.controllers;

import com.cinema.cinema.reservation.dto.CreateReservationInputDto;
import com.cinema.cinema.reservation.dto.NewReservationOutputDto;
import com.cinema.cinema.reservation.models.Reservation;
import com.cinema.cinema.reservation.services.ReservationService;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("new")
    public String newReservation(@RequestParam Integer screeningId, Model model ) {
        model.addAttribute("screeningId", screeningId);

        return "new-reservation";
    }

    @PostMapping()
    public String confirmReservation(CreateReservationInputDto createReservationInputDto) throws BadRequestException {
        Reservation reservation = reservationService.createNewReservation(createReservationInputDto);

        return "redirect:/reservations/" + reservation.getId();
    }

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
        Reservation reservation = reservationService.getReservationById(id);

        model.addAttribute("reservation", reservation);

        return "reservation-details";
    }

}
