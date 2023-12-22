package com.cinema.cinema.movie.services;

import com.cinema.cinema.movie.dto.MovieOutputDto;
import com.cinema.cinema.movie.exceptions.MovieNotFoundException;
import com.cinema.cinema.movie.mappers.MovieMapper;
import com.cinema.cinema.movie.models.Movie;
import com.cinema.cinema.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public List<MovieOutputDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        return movieMapper.moviesToMovieOutputDtos(movies);
    }

    public MovieOutputDto getMovieDetails(Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));

        return movieMapper.movieToMovieOutputDto(movie);
    }
}
