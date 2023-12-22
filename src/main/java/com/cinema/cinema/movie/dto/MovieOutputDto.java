package com.cinema.cinema.movie.dto;

import com.cinema.cinema.movie.enums.MovieGenre;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MovieOutputDto {
    private Integer id;
    private String title;
    private MovieGenre genre;
    private String director;
    private LocalDate releaseDate;
    private Integer length;
    private List<MoviesActorOutputDto> actors;
}
