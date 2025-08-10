# ===== Stage 1: Build the application =====
FROM eclipse-temurin:17-jdk-alpine AS build

# Set working directory
WORKDIR /app

# Copy Gradle wrapper & config first (for better caching)
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copy source code
COPY src src

# Make gradlew executable
RUN chmod +x gradlew

# Clean & build the JAR
RUN ./gradlew clean build --no-daemon

# ===== Stage 2: Run the application =====
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the built JAR from Stage 1
COPY --from=build /app/build/libs/spring-test-demo-0.0.1-SNAPSHOT.jar app.jar

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
