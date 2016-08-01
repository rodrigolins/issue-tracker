# Rationale

This project was made using Spring Boot with Maven.
All dependencies are in the pom.xml file.

# Installation

To install one just need to import this project at Eclipse with Spring Tools Suit (STS) tools plugin or import at STS that can be downloaded at the [Spring website](https://spring.io/tools). 

One need postgreSQL installed. The postgresSQL configuration is located at src/main/resources/application.properties file. Change the database url, username and password accordingly to the user setup.

After importing the project, right click on the project folder and run as... and choose the option Spring Boot App.

The application endpoint:
Developers: http://localhost:8080/api/v1/developers
Stories: http://localhost:8080/api/v1/issues/stories/
Bugs: http://localhost:8080/api/v1/issues/bugs/
Planning: http://localhost:8080/api/v1/planning/

A rudimentary web interface can be accessed at:
http://localhost:8080/tracker/developer/
