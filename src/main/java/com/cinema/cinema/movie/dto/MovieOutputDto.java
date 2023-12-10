package com.cinema.cinema.movie.dto;

import com.cinema.cinema.movie.enums.MovieGenre;
import lombok.Data;

import java.util.List;

@Data
public class MovieOutputDto {
    private Integer id;
    private String title;
    private MovieGenre genre;
    private Integer minutesLength;
    private List<MoviesActorOutputDto> actors;
}
