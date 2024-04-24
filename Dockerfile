# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim AS build
# Set working directory
WORKDIR /app
# Copy the pom.xml file
COPY pom.xml .
# Copy the source code
COPY src src
# Build the application
RUN mvn clean package -DskipTlocServeests

# Stage 2: Create the final image
FROM openjdk:17-alpine
# Set the working directory
WORKDIR /app
# Copy the built JAR file from the previous stage
COPY --from=build /app/target/Location-0.0.1-SNAPSHOT.jar /app/Location.jar
# Expose port 8081
EXPOSE 8081
# Command to run the application
CMD ["java", "-jar", "Location.jar"]
