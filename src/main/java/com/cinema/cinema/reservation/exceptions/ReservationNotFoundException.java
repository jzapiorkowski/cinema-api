package com.cinema.cinema.reservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationNotFoundException extends RuntimeException {
    public  ReservationNotFoundException(Integer id) {
        super("Reservation with id '%s' not found".formatted(id));
    }
}
