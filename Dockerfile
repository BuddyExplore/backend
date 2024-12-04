# Use a base image with OpenJDK 17
FROM openjdk:17-jdk-slim

# Specify the location of the JAR file as a build argument
ARG JAR_FILE=target/*.jar

# Copy the Spring Boot JAR file into the container
COPY ${JAR_FILE} app.jar

# Expose the application port (default Spring Boot port is 8080)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app.jar"]
