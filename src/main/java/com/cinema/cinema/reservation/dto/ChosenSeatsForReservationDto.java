package com.cinema.cinema.reservation.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class ChosenSeatsForReservationDto {
    private Integer screeningId;
    @NotEmpty()
    private List<Integer> seats;
}
