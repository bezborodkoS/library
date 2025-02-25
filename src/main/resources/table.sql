CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(100) NOT NULL
);

CREATE TABLE user_books (
                            user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
                            book_id INTEGER REFERENCES books(id) ON DELETE CASCADE,
                            PRIMARY KEY (user_id, book_id)
);