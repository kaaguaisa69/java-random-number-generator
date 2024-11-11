# Java Random Number Generator

This project implements a simple HTTP server in Java that generates random numbers within a specified range. The application serves an HTML form where users can input a minimum and maximum value, and upon submitting, it generates a random number within that range.

## Features

- **Web Interface**: A form for entering the minimum and maximum values for the random number generation.
- **Random Number Generation**: A server-side API that generates a random number within the user-defined range.
- **Error Handling**: Validates that the minimum value is less than the maximum value and responds with an error if not.

## Technology Stack

- Java 17 (OpenJDK)
- `HttpServer` from the `com.sun.net.httpserver` package for serving the web pages and handling HTTP requests.
- Docker for containerization.

## Files

### `RandomNumberGenerator.java`

This is the main Java file containing the code for the HTTP server and random number generation logic.

#### Key Components:
1. **HttpServer**: A simple HTTP server is created on port `8080`, with two contexts:
   - `/` for serving the HTML form.
   - `/random` for generating a random number based on query parameters (`min` and `max`).
   
2. **HTML Form**: Served via the `/` endpoint, it allows users to enter the minimum and maximum values for random number generation.

3. **Random Number Generator**: The `/random` endpoint reads the `min` and `max` values from the query parameters, validates the range, and generates a random number.

4. **Error Handling**: If the `min` value is greater than the `max` value, a `400` HTTP error is returned with a relevant error message.

### `Dockerfile`

The Dockerfile defines the instructions to build and run the Java Random Number Generator application inside a container.

#### Key Instructions:
- Uses `openjdk:17-jdk-slim` as the base image.
- Copies the `RandomNumberGenerator.java` source code into the container.
- Compiles the Java program and exposes port `8080` for HTTP requests.
- Runs the Java application using the `java` command.

## How to Run the Application

### Prerequisites

- Docker (for containerized execution).
- Java 17 (for local execution).

### Running with Docker

1. **Build the Docker Image**:
   ```bash
   docker build -t random-number-generator .
    ```
2. **Run the Docker Container**:
   ```bash
   docker run -p 8080:8080 random-number-generator
    ```
3. Open your browser and navigate to http://localhost:8080 to access the Random Number Generator interface.

#### Running Locally (Without Docker)
1. **Compile the Java program:**:
   ```bash
   javac RandomNumberGenerator.java
    ```
2. **Compile the Java program:**:
   ```bash
   java RandomNumberGenerator
    ```
3. Open your browser and go to http://localhost:8080 to interact with the application.

### License

This README provides a comprehensive overview of your Java random number generator project, including how to build and run the application, details about the API, and contribution guidelines.
