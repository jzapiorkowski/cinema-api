package com.cinema.cinema.movie.mappers;

import com.cinema.cinema.movie.dto.MovieOutputDto;
import com.cinema.cinema.movie.models.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(source = "minutesLength", target = "length")
    MovieOutputDto movieToMovieOutputDto(Movie movie);

    List<MovieOutputDto> moviesToMovieOutputDtos(List<Movie> movies);
}
