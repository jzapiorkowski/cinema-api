package com.cinema.cinema.movie.controllers;

import com.cinema.cinema.movie.dto.MovieOutputDto;
import com.cinema.cinema.movie.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieOutputDto>> getMovies() {
        List<MovieOutputDto> movies = movieService.getAllMovies();

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieOutputDto> getMovieDetails(@PathVariable Integer id) {
        MovieOutputDto movie = movieService.getMovieDetails(id);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}
