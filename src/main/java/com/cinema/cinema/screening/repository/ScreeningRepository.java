package com.cinema.cinema.screening.repository;

import com.cinema.cinema.screening.models.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
    @Query("SELECT s FROM Screening s WHERE s.date = :targetDate")
    List<Screening> findAllScreeningsByDate(@Param("targetDate") Date targetDate);
}
