package com.cinema.cinema.seat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SeatNotFoundException extends RuntimeException {
    public  SeatNotFoundException(Integer id) {
        super("Seat with id '%s' not found".formatted(id));
    }
}
