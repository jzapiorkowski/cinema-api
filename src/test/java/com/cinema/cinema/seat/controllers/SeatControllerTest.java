package com.cinema.cinema.seat.controllers;

import com.cinema.cinema.reservation.dto.CreateReservationInputDto;
import com.cinema.cinema.seat.dto.SeatOutputDto;
import com.cinema.cinema.seat.services.SeatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SeatControllerTest {
    private MockMvc mockMvc;

    @Mock
    private SeatService seatService;

    @BeforeEach
    void setUp() {
        seatService = mock(SeatService.class);

        mockMvc = MockMvcBuilders.standaloneSetup(new SeatController(seatService))
                .setViewResolvers(new InternalResourceViewResolver("/WEB-INF/views/", ".jsp"))
                .build();
    }

    @Test
    void testChooseSeatsForScreening() throws Exception {
        Integer screeningId = 1;
        List<SeatOutputDto> availableSeats = Arrays.asList(new SeatOutputDto(), new SeatOutputDto());

        when(seatService.getAvailableSeatsForScreening(screeningId)).thenReturn(availableSeats);

        mockMvc.perform(get("/seats/choose")
                        .param("screeningId", String.valueOf(screeningId)))
                .andExpect(status().isOk())
                .andExpect(view().name("choose-seats"))
                .andExpect(model().attribute("availableSeats", availableSeats))
                .andExpect(model().attributeExists("reservationData"));
    }

    @Test
    void testGoToReservationSummary() throws Exception {
        CreateReservationInputDto reservationData = new CreateReservationInputDto();

        mockMvc.perform(post("/seats/choose")
                        .flashAttr("reservationData", reservationData))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reservations/summary"))
                .andExpect(flash().attribute("reservationData", reservationData));

        verifyNoInteractions(seatService);
    }
}
