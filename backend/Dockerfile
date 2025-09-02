#This Dockerfile packages your Spring Boot JAR into a container and 
#runs it with Java inside OpenJDK 17.

#FROM openjdk:17-jdk-slim → Use Java 17 base image.
#WORKDIR /app → Set working directory inside container.
#COPY target/serviceapp-0.0.1-SNAPSHOT.jar app.jar → Copy your built Spring Boot JAR into the container.
#EXPOSE 8080 → App will listen on port 8080.
#ENTRYPOINT ["java", "-jar", "app.jar"] → Run the Spring Boot JAR when container starts.

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/serviceapp-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
