# Test task
# Game _"Bulls and Cows"_
##### Web Application
---
### Desciption
The computer guesses a 4-digit number. The digits are unique.  
User goal is to guess the number.  
The number of attempts is unlimited.  
On every user attempt the computer reports how many digits are accurately guessed (_bull_) and how many digits are guessed without taking into account position (_cow_).  
According to computer's hint the user must guess the number.
### Example
```7328``` -- guessed number
```0819 -- 0B1C
4073 -- 0B2C
5820 -- 0B1C
3429 -- 1B1C
5960 -- 0B0C
7238 -- 2B2C
7328 -- 4B0C (the number if guessed)
```
### Requirements
* The user must register to access the system.
* The user must see his previous attempts.
* The computer maintains users rating (the score is the average attempts amount to guess the number).
* Method of data storage - arbitrary (MySQL database is chosen). 
* It is desirable to let the user select the digits visually, using the mouse.
### Technical requirements
* Use Servlets / Spring, JSP.
* The application should normally be executed under Tomcat 8.
