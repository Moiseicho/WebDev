# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim AS build
# Set working directory
WORKDIR /app
# Copy the pom.xml file
COPY pom.xml .
# Copy the source code
COPY src src
# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-alpine
# Set the working directory
WORKDIR /app
# Copy the built JAR file from the previous stage
COPY --from=build /app/target/location-service-0.0.1-SNAPSHOT.jar /app/LocationService.jar
# Expose port 8082
EXPOSE 8082
# Command to run the application
CMD ["java", "-jar", "LocationService.jar"]