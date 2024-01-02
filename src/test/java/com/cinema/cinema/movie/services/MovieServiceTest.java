package com.cinema.cinema.movie.services;

import com.cinema.cinema.movie.dto.MovieOutputDto;
import com.cinema.cinema.movie.exceptions.MovieNotFoundException;
import com.cinema.cinema.movie.mappers.MovieMapper;
import com.cinema.cinema.movie.models.Movie;
import com.cinema.cinema.movie.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMovies() {
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        List<MovieOutputDto> movieOutputDtos = Arrays.asList(new MovieOutputDto(), new MovieOutputDto());

        when(movieRepository.findAll()).thenReturn(movies);
        when(movieMapper.moviesToMovieOutputDtos(movies)).thenReturn(movieOutputDtos);

        List<MovieOutputDto> result = movieService.getAllMovies();

        assertEquals(movieOutputDtos, result);
        verify(movieRepository, times(1)).findAll();
        verify(movieMapper, times(1)).moviesToMovieOutputDtos(movies);
    }

    @Test
    void testGetMovieDetails() {
        Integer movieId = 1;
        Movie movie = new Movie();
        MovieOutputDto movieOutputDto = new MovieOutputDto();

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
        when(movieMapper.movieToMovieOutputDto(movie)).thenReturn(movieOutputDto);

        MovieOutputDto result = movieService.getMovieDetails(movieId);

        assertEquals(movieOutputDto, result);
        verify(movieRepository, times(1)).findById(movieId);
        verify(movieMapper, times(1)).movieToMovieOutputDto(movie);
    }

    @Test
    void testGetMovieDetails_ThrowsMovieNotFoundException() {
        Integer movieId = 1;

        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> movieService.getMovieDetails(movieId));
        verify(movieRepository, times(1)).findById(movieId);
        verify(movieMapper, never()).movieToMovieOutputDto(any());
    }
}
