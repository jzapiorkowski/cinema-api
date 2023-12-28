package com.cinema.cinema.roles.repository;

import com.cinema.cinema.roles.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Integer> {
}
