package com.cinema.cinema.seat.services;

import com.cinema.cinema.seat.dto.SeatOutputDto;
import com.cinema.cinema.seat.mappers.SeatMapper;
import com.cinema.cinema.seat.models.Seat;
import com.cinema.cinema.seat.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;

    public SeatService(SeatRepository seatRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
    }

    public List<SeatOutputDto> getAvailableSeatsForScreening(Integer screeningId) {
        List<Seat> seats = seatRepository.findAvailableSeatsByScreeningId(screeningId);

        return seatMapper.seatsToSeatOutputDtos(seats);
    }
}
