package com.cinema.cinema.reservation.dto;

import com.cinema.cinema.screening.dto.ScreeningOutputDto;
import com.cinema.cinema.seat.dto.SeatOutputDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationOutputDto {
    private Integer id;
    private LocalDateTime createdAt;
    private ScreeningOutputDto screening;
    private List<SeatOutputDto> seats;
}
