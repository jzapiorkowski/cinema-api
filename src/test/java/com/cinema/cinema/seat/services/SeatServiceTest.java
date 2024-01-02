package com.cinema.cinema.seat.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.cinema.cinema.seat.exceptions.SeatNotFoundException;
import com.cinema.cinema.seat.dto.SeatOutputDto;
import com.cinema.cinema.seat.mappers.SeatMapper;
import com.cinema.cinema.seat.models.Seat;
import com.cinema.cinema.seat.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

class SeatServiceTest {
    private SeatRepository seatRepository;
    private SeatMapper seatMapper;
    private SeatService seatService;

    @BeforeEach
    void setUp() {
        seatRepository = mock(SeatRepository.class);
        seatMapper = mock(SeatMapper.class);
        seatService = new SeatService(seatRepository, seatMapper);
    }

    @Test
    void testGetAvailableSeatsForScreening() {
        Integer screeningId = 1;
        List<Seat> seats = Arrays.asList(new Seat(), new Seat());
        List<SeatOutputDto> seatOutputDtos = Arrays.asList(new SeatOutputDto(), new SeatOutputDto());

        when(seatRepository.findAvailableSeatsByScreeningId(screeningId)).thenReturn(seats);
        when(seatMapper.seatsToSeatOutputDtos(seats)).thenReturn(seatOutputDtos);

        List<SeatOutputDto> result = seatService.getAvailableSeatsForScreening(screeningId);

        assertEquals(seatOutputDtos, result);
    }

    @Test
    void testGetSeatEntityById() {
        Integer seatId = 1;
        Seat seat = new Seat();

        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));

        Seat result = seatService.getSeatEntityById(seatId);

        assertEquals(seat, result);
    }

    @Test
    void testGetSeatEntityById_NotFound() {
        Integer seatId = 1;

        when(seatRepository.findById(seatId)).thenReturn(Optional.empty());

        assertThrows(SeatNotFoundException.class, () -> seatService.getSeatEntityById(seatId));
    }

    @Test
    void testCheckIfSeatsAreAvailable_AllAvailable() {
        Integer screeningId = 1;
        List<Integer> seatIds = Arrays.asList(1, 2);
        List<SeatOutputDto> availableSeats = Arrays.asList(new SeatOutputDto(1, 1, 1), new SeatOutputDto(2, 2, 2));

        when(seatService.getAvailableSeatsForScreening(screeningId)).thenReturn(availableSeats);

        boolean result = seatService.checkIfSeatsAreAvailable(screeningId, seatIds);

        assertTrue(result);
    }

    @Test
    void testCheckIfSeatsAreAvailable_NotAllAvailable() {
        Integer screeningId = 1;
        List<Integer> seatIds = Arrays.asList(1, 2);
        List<SeatOutputDto> availableSeats = Collections.singletonList(new SeatOutputDto(1, 1, 1));

        when(seatService.getAvailableSeatsForScreening(screeningId)).thenReturn(availableSeats);

        boolean result = seatService.checkIfSeatsAreAvailable(screeningId, seatIds);

        assertFalse(result);
    }
}
