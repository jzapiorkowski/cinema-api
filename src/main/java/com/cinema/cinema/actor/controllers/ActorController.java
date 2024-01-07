package com.cinema.cinema.actor.controllers;

import com.cinema.cinema.actor.services.ActorService;
import com.cinema.cinema.actor.dto.ActorOutputDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    List<ActorOutputDto> getActors() {
        return actorService.getAllActors();
    }
}
