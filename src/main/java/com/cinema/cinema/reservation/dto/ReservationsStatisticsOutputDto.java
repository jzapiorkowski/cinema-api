package com.cinema.cinema.reservation.dto;

import lombok.Data;

@Data
public class ReservationsStatisticsOutputDto {
    private Integer reservationsNumber;
    private Integer reservedSeats;
    private Integer numberOfMovies;
}
