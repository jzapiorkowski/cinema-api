package com.cinema.cinema.movie.models;

import com.cinema.cinema.actor.models.Actor;
import com.cinema.cinema.movie.enums.MovieGenre;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue
    @Column(name = "movie_id")
    private Integer id;

    @Column
    private String title;

    @Column
    @Enumerated(EnumType.STRING)
    private MovieGenre genre;

    @Column(name = "minutes_length")
    private Integer minutesLength;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors;
}



//    @Column
//    private idk trailer

//    @Column
//    private idk imageGallery