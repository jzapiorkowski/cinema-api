package com.cinema.cinema.screening.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ScreeningNotFoundException extends RuntimeException {
    public  ScreeningNotFoundException(Integer id) {
        super("Screening with id '%s' not found".formatted(id));
    }
}
