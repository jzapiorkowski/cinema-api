package com.cinema.cinema.screening.dto;

import com.cinema.cinema.movie.enums.MovieGenre;
import lombok.Data;

import java.util.List;

@Data
public class ScreeningMovieOutputDto {
    private Integer id;
    private String title;
    private MovieGenre genre;
    private Integer minutesLength;
    private List<ScreeningMovieActorOutputDto> actors;
}
