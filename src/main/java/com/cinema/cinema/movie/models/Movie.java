package com.cinema.cinema.movie.models;

import com.cinema.cinema.actor.models.Actor;
import com.cinema.cinema.movie.enums.MovieGenre;
import com.cinema.cinema.screening.models.Screening;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
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

    @Column
    private String director;

    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private LocalDate releaseDate;

    @Column(name = "minutes_length")
    private Integer minutesLength;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Screening> screenings;
}
