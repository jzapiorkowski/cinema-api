package com.cinema.cinema.actor.models;

import com.cinema.cinema.movie.models.Movie;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue
    @Column(name = "actor_id")
    private Integer id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}
