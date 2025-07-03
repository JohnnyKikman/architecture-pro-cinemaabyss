# Stage 1: Build the application using Gradle and Kotlin DSL
FROM gradle:8.7-jdk21 AS builder

# Set the working directory in the build stage
WORKDIR /build

# Copy Gradle files first to leverage Docker cache
COPY settings.gradle.kts .
COPY build.gradle.kts .

# Download dependencies to speed up future builds
RUN gradle dependencies || true

# Copy the rest of the source code
COPY . .

# Build the application JAR (adjust task name if needed, e.g., shadowJar if using Shadow plugin)
RUN gradle clean build

# Stage 2: Run the application
FROM eclipse-temurin:21-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /build/build/libs/*.jar proxy.jar

# Expose port 8000
EXPOSE 8000

# Run the application
ENTRYPOINT ["java", "-jar", "proxy.jar"]