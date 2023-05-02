FROM openjdk:17

COPY target/social-media-api-0.0.1-SNAPSHOT.jar social-media-api-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java" , "-jar" , "/social-media-api-0.0.1-SNAPSHOT.jar"]

