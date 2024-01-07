package com.cinema.cinema.seat.controllers;

import com.cinema.cinema.seat.dto.SeatOutputDto;
import com.cinema.cinema.seat.services.SeatService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("available")
    public ResponseEntity<List<SeatOutputDto>> chooseSeatsForScreening(@RequestParam @NotNull Integer screeningId) {
        List<SeatOutputDto> availableSeats = seatService.getAvailableSeatsForScreening(screeningId);

        return new ResponseEntity<>(availableSeats, HttpStatus.OK);
    }
}
