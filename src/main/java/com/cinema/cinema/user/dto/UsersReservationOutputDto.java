package com.cinema.cinema.user.dto;

import com.cinema.cinema.screening.models.Screening;
import com.cinema.cinema.screening.models.Screening;
import com.cinema.cinema.seat.models.Seat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UsersReservationOutputDto {
    private Integer id;
    private LocalDateTime createdAt;
//    private Screening screening;
//    private List<Seat> seats;
}
