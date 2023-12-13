package com.cinema.cinema.screening.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScreeningOutputDto {
    private Integer id;
    private LocalDate date;
    private LocalTime startHour;
    private ScreeningMovieOutputDto movie;
}
