CREATE DATABASE IF NOT EXISTS artwork_db;
USE artwork_db;

CREATE TABLE IF NOT EXISTS artworks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    artist VARCHAR(100),
    price DOUBLE
);