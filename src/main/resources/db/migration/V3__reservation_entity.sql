CREATE TABLE reservations (
    reservation_id         INTEGER NOT NULL PRIMARY KEY,
    screening_screening_id INTEGER REFERENCES screenings(screening_id),
    created_at             TIMESTAMP
);


CREATE TABLE reservations_seats (
    PRIMARY KEY (reservation_id, seat_id),
    reservation_id INTEGER REFERENCES reservations(reservation_id),
    seat_id        INTEGER REFERENCES seats(seat_id)
);