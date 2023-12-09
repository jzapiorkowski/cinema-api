package com.cinema.cinema.movie.repository;

import com.cinema.cinema.movie.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
