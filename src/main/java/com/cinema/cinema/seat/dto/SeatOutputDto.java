package com.cinema.cinema.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatOutputDto {
    private Integer id;
    private Integer row;
    private Integer seatNumber;
}
