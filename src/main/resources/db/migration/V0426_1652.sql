DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    username varchar(30),
    password varchar(255),
    PRIMARY KEY (username)
);

INSERT INTO users (username, password)
VALUES ('Bond', 'password');