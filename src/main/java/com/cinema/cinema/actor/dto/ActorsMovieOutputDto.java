package com.cinema.cinema.actor.dto;

import com.cinema.cinema.movie.enums.MovieGenre;
import lombok.Data;

@Data
public class ActorsMovieOutputDto {
    private Integer id;
    private String title;
    private MovieGenre genre;
    private Integer minutesLength;
}
