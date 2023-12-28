CREATE TABLE users (
    user_id   INTEGER NOT NULL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    email     VARCHAR(255),
    password  VARCHAR(255)
);

CREATE TABLE roles (
    role_id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE users_roles (
    PRIMARY KEY (user_id, role_id),
    user_id INTEGER REFERENCES users(user_id),
    role_id INTEGER REFERENCES roles(role_id)
)