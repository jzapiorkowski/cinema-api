package com.cinema.cinema.movie.controllers;

import com.cinema.cinema.movie.dto.MovieOutputDto;
import com.cinema.cinema.movie.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller()
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String getMovies(Model model) {
        List<MovieOutputDto> movies = movieService.getAllMovies();

        model.addAttribute("movies", movies);

        return "movies";
    }

    @GetMapping("{id}")
    public String getMovieDetails(@PathVariable Integer id, Model model) {
        MovieOutputDto movie = movieService.getMovieDetails(id);

        model.addAttribute("movie", movie);

        return "movie";
    }
}
