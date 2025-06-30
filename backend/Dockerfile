# Use a lightweight Java image
FROM openjdk:17-jdk-slim

# Set working directory in container
WORKDIR /app

# Correct path (jar file is in this folder if build context is backend)
COPY target/*.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
