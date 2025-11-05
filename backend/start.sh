#!/bin/bash

echo "========================================"
echo " Compiler-AI Spring Boot Backend"
echo "========================================"
echo ""

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed or not in PATH"
    echo "Please install Java 17 or higher"
    echo "  Mac: brew install openjdk@17"
    echo "  Linux: sudo apt install openjdk-17-jdk"
    exit 1
fi

echo "Java found!"
java -version
echo ""

# Check if .env file exists
if [ ! -f .env ]; then
    echo "WARNING: .env file not found!"
    echo "Please create a .env file with the following variables:"
    echo "  MONGO_URI=mongodb://localhost:27017/compiler-ai"
    echo "  JWT_SECRET=your-secret-key-here"
    echo "  COMPILER_URL=http://localhost:8000"
    echo "  GEMINI_API_KEY=your-gemini-api-key"
    echo "  PORT=5050"
    echo ""
    read -p "Press enter to continue anyway..."
fi

echo "Starting Spring Boot application..."
echo ""

# Use Maven wrapper to run the application
if [ -f ./mvnw ]; then
    chmod +x ./mvnw
    ./mvnw spring-boot:run
else
    # Fallback to system Maven
    mvn spring-boot:run
fi
