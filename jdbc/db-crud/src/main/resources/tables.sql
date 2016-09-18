CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    name VARCHAR(16),
    surname VARCHAR(16),
    birthdate DATE
);

CREATE TABLE friendships (
    userid1 BIGINT,
    userid2 BIGINT,
    timestamp TIMESTAMP,
    PRIMARY KEY (userid1, userid2),
    FOREIGN KEY (userid1) REFERENCES users(id),
    FOREIGN KEY (userid2) REFERENCES users(id)
);

CREATE TABLE posts (
    id BIGINT PRIMARY KEY,
    userid BIGINT,
    text VARCHAR(256),
    timestamp TIMESTAMP,
    FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE likes (
    postid BIGINT,
    userid BIGINT,
    timestamp TIMESTAMP,
    PRIMARY KEY (postid, userid),
    FOREIGN KEY (postid) REFERENCES posts(id),
    FOREIGN KEY (userid) REFERENCES users(id)
);

DROP TABLE likes;
DROP TABLE posts;
DROP TABLE friendships;
DROP TABLE users;