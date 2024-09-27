# Stage 1: Build the Spring Boot app using a valid Maven image with OpenJDK 17
FROM maven:3.8.8-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the project files and build the app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the Spring Boot app
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage to the final stage
COPY --from=build /app/target/my-spring-boot-app.jar /app/app.jar

# Expose port 8080 (optional, for documentation purposes)
EXPOSE 8080

# Set the environment variable for the port (Render sets this dynamically)
ENV PORT 8080

# Run the Spring Boot app with the dynamically assigned port
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
CMD ["--server.port=${PORT}"]
