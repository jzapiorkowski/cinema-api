package com.cinema.cinema.actor.services;

import com.cinema.cinema.actor.dto.ActorOutputDto;
import com.cinema.cinema.actor.mappers.ActorMapper;
import com.cinema.cinema.actor.models.Actor;
import com.cinema.cinema.actor.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    ActorMapper actorMapper;

    public List<ActorOutputDto> getAllActors() {
        List<Actor> actors = actorRepository.findAll();
        return actorMapper.actorsToActorOutputDtos(actors);
    }
}
