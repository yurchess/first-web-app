DROP DATABASE IF EXISTS mitroshin_game;

CREATE DATABASE mitroshin_game;

USE mitroshin_game;

CREATE TABLE User (
	Login varchar(15) NOT NULL PRIMARY KEY,
    Password varchar(15) NOT NULL
);

CREATE TABLE Score (
	ScoreID INT NOT NULL AUTO_INCREMENT,
    Login varchar(15) NOT NULL,
    Score FLOAT NOT NULL DEFAULT '0',
    AteemptsCount INT NOT NULL DEFAULT '0',
    
    PRIMARY KEY (ScoreID)
    /*FOREIGN KEY (Login) REFERENCES User (Login)*/
);