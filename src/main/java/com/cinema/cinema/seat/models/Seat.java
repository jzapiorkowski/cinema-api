package com.cinema.cinema.seat.models;

import com.cinema.cinema.reservation.models.Reservation;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue
    @Column(name = "seat_id")
    private Integer id;

    @Column
    private Integer row;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @ManyToMany(mappedBy = "seats")
    private List<Reservation> reservations;
}
