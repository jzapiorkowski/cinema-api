CREATE TABLE actors
(
    actor_id INTEGER NOT NULL PRIMARY KEY,
    name     VARCHAR(255)
);

CREATE TYPE movie_genre AS ENUM (
    'ACTION', 'COMEDY', 'DRAMA', 'ROMANCE', 'HORROR', 'SCIENCE_FICTION',
    'FANTASY', 'ANIMATION', 'DOCUMENTARY', 'THRILLER', 'CRIME', 'ADVENTURE',
    'FAMILY', 'HISTORICAL', 'MUSICAL', 'MYSTERY', 'WAR', 'WESTERN'
    );

create table movies
(
    movie_id       INTEGER NOT NULL PRIMARY KEY,
    genre          movie_genre,
    minutes_length INTEGER,
    title          VARCHAR(255)
);

CREATE TABLE movies_actors
(
    PRIMARY KEY (movie_id, actor_id),
    movie_id INTEGER REFERENCES movies(movie_id),
    actor_id INTEGER REFERENCES actors(actor_id)

);

CREATE TABLE screenings
(
    screening_id   INTEGER NOT NULL PRIMARY KEY,
    date           DATE,
    start_hour     TIME(6),
    movie_movie_id INTEGER REFERENCES movies(movie_id)
);

CREATE TABLE seats
(
    seat_id     INTEGER NOT NULL PRIMARY KEY,
    row         INTEGER,
    seat_number INTEGER
);
