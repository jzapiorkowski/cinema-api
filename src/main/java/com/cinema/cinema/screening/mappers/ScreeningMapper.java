package com.cinema.cinema.screening.mappers;

import com.cinema.cinema.screening.dto.ScreeningOutputDto;
import com.cinema.cinema.screening.models.Screening;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScreeningMapper {
    List<ScreeningOutputDto> screeningsToScreeningOutputDtos(List<Screening> screenings);
}
