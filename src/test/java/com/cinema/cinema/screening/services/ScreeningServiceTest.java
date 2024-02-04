package com.cinema.cinema.screening.services;

import com.cinema.cinema.screening.dto.ScreeningOutputDto;
import com.cinema.cinema.screening.mappers.ScreeningMapper;
import com.cinema.cinema.screening.models.Screening;
import com.cinema.cinema.screening.repository.ScreeningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ScreeningServiceTest {

    @Mock
    private ScreeningRepository screeningRepository;

    @Mock
    private ScreeningMapper screeningMapper;

    @InjectMocks
    private ScreeningService screeningService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testGetAllScreenings() {
//        List<Screening> screenings = Arrays.asList(new Screening(), new Screening());
//        when(screeningRepository.findAll()).thenReturn(screenings);
//        when(screeningMapper.screeningsToScreeningOutputDtos(screenings)).thenReturn(Arrays.asList(new ScreeningOutputDto(), new ScreeningOutputDto()));
//
//        List<ScreeningOutputDto> result = screeningService.getAllScreenings();
//
//        assertEquals(screenings.size(), result.size());
//        verify(screeningMapper, times(1)).screeningsToScreeningOutputDtos(screenings);
//    }

//    @Test
//    void testGetScreeningsByDate() {
//        LocalDate date = LocalDate.now();
//        List<Screening> screenings = Arrays.asList(new Screening(), new Screening());
//        when(screeningRepository.findAllScreeningsByDate(date)).thenReturn(screenings);
//        when(screeningMapper.screeningsToScreeningOutputDtos(screenings)).thenReturn(Arrays.asList(new ScreeningOutputDto(), new ScreeningOutputDto()));
//
//        List<ScreeningOutputDto> result = screeningService.getScreeningsByDate(date);
//
//        assertEquals(screenings.size(), result.size());
//        verify(screeningMapper, times(1)).screeningsToScreeningOutputDtos(screenings);
//    }

    @Test
    void testGetScreeningById() {
        Integer id = 1;
        Screening screening = new Screening();
        when(screeningRepository.findById(id)).thenReturn(Optional.of(screening));
        when(screeningMapper.screeningToScreeningOutputDto(screening)).thenReturn(new ScreeningOutputDto());

        ScreeningOutputDto result = screeningService.getScreeningById(id);

        verify(screeningMapper, times(1)).screeningToScreeningOutputDto(screening);
        assertEquals(new ScreeningOutputDto(), result);
    }

    @Test
    void testGetScreeningEntityById() {
        Integer id = 1;
        Screening screening = new Screening();
        when(screeningRepository.findById(id)).thenReturn(Optional.of(screening));

        Screening result = screeningService.getScreeningEntityById(id);

        assertEquals(screening, result);
    }
}
