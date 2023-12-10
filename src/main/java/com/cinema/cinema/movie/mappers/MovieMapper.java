package com.cinema.cinema.movie.mappers;

import com.cinema.cinema.movie.dto.MovieOutputDto;
import com.cinema.cinema.movie.models.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    List<MovieOutputDto> moviesToMovieOutputDtos(List<Movie> movies);
}
