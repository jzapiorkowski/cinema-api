package com.cinema.cinema.actor.services;

import com.cinema.cinema.actor.dto.ActorOutputDto;
import com.cinema.cinema.actor.mappers.ActorMapper;
import com.cinema.cinema.actor.models.Actor;
import com.cinema.cinema.actor.repository.ActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ActorServiceTest {

    @Mock
    private ActorRepository actorRepository;

    @Mock
    private ActorMapper actorMapper;

    @InjectMocks
    private ActorService actorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllActors() {
        List<Actor> mockActors = Arrays.asList(new Actor(), new Actor());
        List<ActorOutputDto> expectedActorOutputDtos = Arrays.asList(new ActorOutputDto(), new ActorOutputDto());

        when(actorRepository.findAll()).thenReturn(mockActors);
        when(actorMapper.actorsToActorOutputDtos(mockActors)).thenReturn(expectedActorOutputDtos);

        List<ActorOutputDto> result = actorService.getAllActors();

        assertEquals(expectedActorOutputDtos, result);
        verify(actorRepository, times(1)).findAll();
        verify(actorMapper, times(1)).actorsToActorOutputDtos(mockActors);
    }
}
