package com.cinema.cinema.seat.controllers;

import com.cinema.cinema.reservation.dto.CreateReservationInputDto;
import com.cinema.cinema.seat.dto.SeatOutputDto;
import com.cinema.cinema.seat.services.SeatService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/seats")
public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("choose")
    public String chooseSeatsForScreening(@RequestParam @NotNull Integer screeningId, Model model) {
        List<SeatOutputDto> availableSeats = seatService.getAvailableSeatsForScreening(screeningId);

        CreateReservationInputDto reservationData = new CreateReservationInputDto();
        reservationData.setScreening(screeningId);

        model.addAttribute("availableSeats", availableSeats);
        model.addAttribute("reservationData", reservationData);

        return "choose-seats";
    }

    @PostMapping("choose")
    public String goToReservationSummary(@Valid CreateReservationInputDto reservationData, RedirectAttributes redirectAttributes) {
         redirectAttributes.addFlashAttribute("reservationData", reservationData);

        return "redirect:/reservations/summary";
    }
}
