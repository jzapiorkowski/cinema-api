package com.cinema.cinema.screening.models;

import com.cinema.cinema.movie.models.Movie;
import com.cinema.cinema.reservation.models.Reservation;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Table(name = "screenings")
public class Screening {
    @Id
    @GeneratedValue
    @Column(name = "screening_id")
    private Integer id;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Column(name = "start_hour")
    @Temporal(TemporalType.TIME)
    private LocalTime startHour;

    @ManyToOne
    private Movie movie;

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}
