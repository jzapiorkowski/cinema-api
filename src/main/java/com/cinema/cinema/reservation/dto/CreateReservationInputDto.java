package com.cinema.cinema.reservation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateReservationInputDto {
    @NotEmpty
    private List<Integer> seats;
    @NotNull
    private Integer screening;
}
