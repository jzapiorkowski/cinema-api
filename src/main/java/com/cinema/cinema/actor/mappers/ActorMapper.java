package com.cinema.cinema.actor.mappers;

import com.cinema.cinema.actor.dto.ActorOutputDto;
import com.cinema.cinema.actor.models.Actor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    List<ActorOutputDto> actorsToActorOutputDtos(List<Actor> actor);
}
