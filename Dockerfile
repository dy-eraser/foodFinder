# Use a minimal Java runtime image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy the built jar file into the container
COPY target/*.jar app.jar

# Expose port
EXPOSE 8118

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
