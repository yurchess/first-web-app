DROP DATABASE IF EXISTS mitroshin_game;

CREATE DATABASE mitroshin_game;

USE mitroshin_game;

CREATE TABLE User (
	UserId INT NOT NULL AUTO_INCREMENT,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(50),user
    
    PRIMARY KEY(UserId)
);