# Use a base image with OpenJDK 17
FROM openjdk:17-jdk-slim

# Specify the location of the JAR file as a build argument
COPY target/userService-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (default Spring Boot port is 8080)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app.jar"]
