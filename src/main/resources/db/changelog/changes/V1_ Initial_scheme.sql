CREATE TABLE users
(
    id         BIGINT PRIMARY KEY,
    email      VARCHAR(50) UNIQUE NOT NULL,
    password   VARCHAR(50)        NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE authors
(
    id         BIGINT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    biography  TEXT        NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE books
(
    id           BIGINT PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    isbn         VARCHAR(255) NOT NULL UNIQUE,
    publish_year INTEGER      NOT NULL,
    status       VARCHAR(50)  NOT NULL,
    authors_id   BIGINT       NOT NULL,
    user_id      BIGINT       NOT NULL,

    CONSTRAINT check_status CHECK ( status IN ('PLANNED', 'READING', 'FINISHED')),

    CONSTRAINT fk_books_authors
        FOREIGN KEY (authors_id)
            REFERENCES authors (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_books_users
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
);