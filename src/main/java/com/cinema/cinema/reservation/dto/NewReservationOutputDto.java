package com.cinema.cinema.reservation.dto;

import com.cinema.cinema.screening.dto.ScreeningOutputDto;
import com.cinema.cinema.seat.dto.SeatOutputDto;
import lombok.Data;

import java.util.List;

@Data
public class NewReservationOutputDto {
    private List<SeatOutputDto> seats;
    private ScreeningOutputDto screening;
}
