package com.cinema.cinema.reservation.controllers;

import com.cinema.cinema.reservation.dto.ChosenSeatsForReservationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    @GetMapping("new")
    public String newReservation(@RequestParam Integer screeningId, Model model ) {
        model.addAttribute("screeningId", screeningId);

        return "new-reservation";
    }

    @GetMapping("summary")
    public String reservationSummary(@ModelAttribute("reservationData") ChosenSeatsForReservationDto reservationData, Model model) {
        return "reservation-summary";
    }
}
