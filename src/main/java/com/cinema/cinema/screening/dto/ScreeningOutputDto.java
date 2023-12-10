package com.cinema.cinema.screening.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class ScreeningOutputDto {
    private Integer id;
    private Date date;
    private LocalTime startHour;
    private ScreeningMovieOutputDto movie;
}
