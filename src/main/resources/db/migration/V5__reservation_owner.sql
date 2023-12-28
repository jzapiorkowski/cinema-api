ALTER TABLE reservations
ADD COLUMN user_user_id INTEGER REFERENCES users(user_id);