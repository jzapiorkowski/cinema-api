package com.cinema.cinema.screening.controllers;

import com.cinema.cinema.screening.dto.ScreeningOutputDto;
import com.cinema.cinema.screening.services.ScreeningService;
import jakarta.annotation.Nullable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/screenings")
public class ScreeningController {
    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping
    ResponseEntity<List<ScreeningOutputDto>> getAllScreenings(
            @RequestParam(required = false) @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<ScreeningOutputDto> screenings;
        if (date == null) {
            screenings = screeningService.getAllScreenings();
        } else {
            screenings = screeningService.getScreeningsByDate(date);
        }

        return new ResponseEntity<>(screenings, HttpStatus.OK);
    }
}
