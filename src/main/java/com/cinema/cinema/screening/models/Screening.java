package com.cinema.cinema.screening.models;

import com.cinema.cinema.movie.models.Movie;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

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
    private Date date;

    @Column(name = "start_hour")
    @Temporal(TemporalType.TIME)
    private LocalTime startHour;

    @ManyToOne
    private Movie movie;
}
