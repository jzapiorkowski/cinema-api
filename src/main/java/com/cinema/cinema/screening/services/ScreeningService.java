package com.cinema.cinema.screening.services;

import com.cinema.cinema.screening.dto.ScreeningOutputDto;
import com.cinema.cinema.screening.exceptions.ScreeningNotFoundException;
import com.cinema.cinema.screening.mappers.ScreeningMapper;
import com.cinema.cinema.screening.repository.ScreeningRepository;
import com.cinema.cinema.screening.models.Screening;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScreeningService {
    private final ScreeningRepository screeningRepository;
    private final ScreeningMapper screeningMapper;

    public ScreeningService(ScreeningRepository screeningRepository, ScreeningMapper screeningMapper) {
        this.screeningRepository = screeningRepository;
        this.screeningMapper = screeningMapper;
    }

    public List<ScreeningOutputDto> getAllScreenings() {
        List<Screening> screenings = screeningRepository.findAll();
        return screeningMapper.screeningsToScreeningOutputDtos(screenings);
    }

    public List<ScreeningOutputDto> getScreeningsByDate(LocalDate date) {
        List<Screening> screenings = screeningRepository.findAllScreeningsByDate(date);
        return screeningMapper.screeningsToScreeningOutputDtos(screenings);
    }

    public ScreeningOutputDto getScreeningById(Integer id) {
        Screening screening = getScreeningEntityById(id);
        return screeningMapper.screeningToScreeningOutputDto(screening);
    }

    public Screening getScreeningEntityById(Integer id) {
        return screeningRepository.findById(id).orElseThrow(() -> new ScreeningNotFoundException(id));
    }
}
