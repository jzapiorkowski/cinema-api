package com.cinema.cinema.reservation.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.List;

@Data
public class UpdateReservationInputDto {
    @Nullable
    private Integer screeningId;

    private List<Integer> seats;
}
