@echo off
echo ========================================
echo  Compiler-AI Spring Boot Backend
echo ========================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 17 or higher from https://adoptium.net/
    pause
    exit /b 1
)

echo Java found!
echo.

REM Check if .env file exists
if not exist .env (
    echo WARNING: .env file not found!
    echo Please create a .env file with the following variables:
    echo   MONGO_URI=mongodb://localhost:27017/compiler-ai
    echo   JWT_SECRET=your-secret-key-here
    echo   COMPILER_URL=http://localhost:8000
    echo   GEMINI_API_KEY=your-gemini-api-key
    echo   PORT=5050
    echo.
    pause
)

echo Starting Spring Boot application...
echo.

REM Use Maven wrapper to run the application
if exist mvnw.cmd (
    call mvnw.cmd spring-boot:run
) else (
    REM Fallback to system Maven
    mvn spring-boot:run
)

pause
