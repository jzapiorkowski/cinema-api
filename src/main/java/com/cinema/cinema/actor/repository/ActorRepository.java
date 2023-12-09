package com.cinema.cinema.actor.repository;

import com.cinema.cinema.actor.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
