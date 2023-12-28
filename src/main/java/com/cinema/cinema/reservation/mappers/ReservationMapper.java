package com.cinema.cinema.reservation.mappers;

import com.cinema.cinema.reservation.dto.ReservationOutputDto;
import com.cinema.cinema.reservation.models.Reservation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationOutputDto reservationToReservationOutputDto(Reservation reservations);

    List<ReservationOutputDto> reservationsToReservationOutputDtos(List<Reservation> reservations);
}
