# social-media-api

1. Clone the application
git clone https://github.com/youngAndMad/social-media-api

<hr>
2. Create PostgreSQL database
create database blogapi
run src/main/resources/database.sql

<hr>
3. Change mysql username and password as per your installation
open src/main/resources/application.properties
change spring.datasource.username and spring.datasource.password as per your postgres installation

<hr>
4. Run the app using maven
mvn spring-boot:run
The app will start running at http://localhost:8080

<hr>

#Details
Web messenger clone. You can add friends or block other users, add
posts, open channels and comment on various posts. Real-time chatting
is provided using Web Sockets. Authorization and authentication is done using JWT Web Tokens. 

Used technologies: Java, JavaScript, Spring Boot, Spring Data JPA, Spring Data JDBC, PostgreSQL, Maven, Rest API, Web Sockets, JWT
Token, Spring Security, Docker, Mail Sending(With smtp server)

http://localhost:8080/v2/api-docs => for swagger ingormation


all endpoints enumarated in entpoints.txt
