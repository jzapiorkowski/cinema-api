package com.cinema.cinema.screening.services;

import com.cinema.cinema.screening.dto.ScreeningOutputDto;
import com.cinema.cinema.screening.mappers.ScreeningMapper;
import com.cinema.cinema.screening.repository.ScreeningRepository;
import com.cinema.cinema.screening.models.Screening;
import org.springframework.stereotype.Service;

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

//        if (screenings.isEmpty()) {
//            throw new NoScreeningsException("No screenings found");
//        }

        return screeningMapper.screeningsToScreeningOutputDtos(screenings);
    }
}
