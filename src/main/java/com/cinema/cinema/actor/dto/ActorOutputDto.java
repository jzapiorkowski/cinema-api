package com.cinema.cinema.actor.dto;

import lombok.Data;

import java.util.List;

@Data
public class ActorOutputDto {
    private Integer id;
    private String name;
    private List<ActorsMovieOutputDto> movies;
}
