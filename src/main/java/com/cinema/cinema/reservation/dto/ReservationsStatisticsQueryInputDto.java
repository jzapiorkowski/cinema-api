package com.cinema.cinema.reservation.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationsStatisticsQueryInputDto {
    @Nullable
    @Min(1)
    @Max(12)
    private Integer month;
    @Nullable
    @Min(1)
    @Max(31)
    private Integer day;
}
