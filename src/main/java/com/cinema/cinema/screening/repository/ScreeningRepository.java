package com.cinema.cinema.screening.repository;

import com.cinema.cinema.screening.models.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
}
