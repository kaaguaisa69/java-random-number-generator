# Use an official OpenJDK image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Java source file to the container
COPY RandomNumberGenerator.java .

# Compile the Java program
RUN javac RandomNumberGenerator.java

# Expose port 8080 to the host
EXPOSE 8080

# Define the command to run the application
CMD ["java", "RandomNumberGenerator"]
