package com.cinema.cinema.seat.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue()
    @Column(name = "movie_id")
    private int id;

    @Column
    private int column;
}
