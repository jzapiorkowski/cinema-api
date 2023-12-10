package com.cinema.cinema.screening.controllers;

import com.cinema.cinema.screening.dto.ScreeningOutputDto;
import com.cinema.cinema.screening.services.ScreeningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/screenings")
public class ScreeningController {
    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping
    ResponseEntity<List<ScreeningOutputDto>> getAllScreenings() {
        List<ScreeningOutputDto> screenings = screeningService.getAllScreenings();
        return new ResponseEntity<>(screenings, HttpStatus.OK);
    }
}
