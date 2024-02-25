# Use AdoptOpenJDK 11 as base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/cloudruids.shop-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "app.jar"]
