package com.cinema.cinema.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationsPerUserStatisticsOutputDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private Long reservationCount;
}
