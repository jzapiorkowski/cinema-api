package com.cinema.cinema.screening.controllers;

import com.cinema.cinema.screening.dto.ScreeningOutputDto;
import com.cinema.cinema.screening.services.ScreeningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ScreeningControllerTest {

    @Mock
    private ScreeningService screeningService;

    @Mock
    private Model model;

    @InjectMocks
    private ScreeningController screeningController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllScreeningsWithNullDate() {
        LocalDate date = null;
        List<ScreeningOutputDto> expectedScreenings = Arrays.asList(new ScreeningOutputDto(), new ScreeningOutputDto());

        when(screeningService.getAllScreenings()).thenReturn(expectedScreenings);

        String viewName = screeningController.getAllScreenings(date, model);

        assertEquals("screenings", viewName);
        verify(screeningService, times(1)).getAllScreenings();
        verify(screeningService, never()).getScreeningsByDate(any(LocalDate.class));
        verify(model, times(1)).addAttribute("screenings", expectedScreenings);
    }

    @Test
    void testGetAllScreeningsWithNonNullDate() {
        LocalDate date = LocalDate.now();
        List<ScreeningOutputDto> expectedScreenings = Arrays.asList(new ScreeningOutputDto(), new ScreeningOutputDto());

        when(screeningService.getScreeningsByDate(date)).thenReturn(expectedScreenings);

        String viewName = screeningController.getAllScreenings(date, model);

        assertEquals("screenings", viewName);
        verify(screeningService, never()).getAllScreenings();
        verify(screeningService, times(1)).getScreeningsByDate(date);
        verify(model, times(1)).addAttribute("screenings", expectedScreenings);
    }
}
