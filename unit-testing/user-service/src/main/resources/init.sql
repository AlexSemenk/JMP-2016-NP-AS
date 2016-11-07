CREATE DATABASE IF NOT EXISTS user;

USE user;

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(32),
    firstName VARCHAR(32),
    lastName VARCHAR(32),
    email VARCHAR(64),
    password VARCHAR(64)
);