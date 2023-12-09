package com.cinema.cinema.seat.models;

import jakarta.persistence.*;
import lombok.Data;

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
}
