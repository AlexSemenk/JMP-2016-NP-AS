CREATE TABLE IF NOT EXISTS apple (
    id BIGINT,
    color VARCHAR(16),
    manufacturer VARCHAR(16),
    price SMALLINT,
    date_of_harvesting DATE
);

CREATE TABLE IF NOT EXISTS computer (
    id BIGINT,
    manufacturer VARCHAR(16),
    cores_number TINYINT,
    date_of_manufacture DATE
);

CREATE TABLE IF NOT EXISTS play (
    id BIGINT,
    name VARCHAR(32),
    theatre VARCHAR(32),
    timestamp TIMESTAMP
)