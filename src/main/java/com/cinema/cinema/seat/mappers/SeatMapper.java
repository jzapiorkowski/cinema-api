package com.cinema.cinema.seat.mappers;

import com.cinema.cinema.seat.dto.SeatOutputDto;
import com.cinema.cinema.seat.models.Seat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    SeatOutputDto seatToSeatOutputDto(Seat seat);
    List<SeatOutputDto> seatsToSeatOutputDtos(List<Seat> seats);

}
