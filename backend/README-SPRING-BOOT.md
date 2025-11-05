# Spring Boot Backend Migration

This backend has been migrated from Node.js to Spring Boot.

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MongoDB (connection string in .env)

## Environment Variables

Create a `.env` file in the backend directory with:

```env
MONGO_URI=mongodb://localhost:27017/compiler-ai
JWT_SECRET=your-secret-key-here-make-it-long-and-random
COMPILER_URL=http://localhost:8000
GEMINI_API_KEY=your-gemini-api-key
PORT=5050
```

## Running Locally

### With Maven

```bash
# Install dependencies
mvn clean install

# Run the application
mvn spring-boot:run
```

### With Java

```bash
# Build the JAR
mvn clean package

# Run the JAR
java -jar target/backend-1.0.0.jar
```

## Running with Docker

```bash
# Build the image
docker build -t compiler-ai-backend .

# Run the container
docker run -p 5050:5050 --env-file .env compiler-ai-backend
```

## Running with Docker Compose

From the root directory:

```bash
docker-compose up --build
```

## API Endpoints

### Authentication
- `POST /api/register` - Register a new user
- `POST /api/login` - Login user
- `GET /api/me` - Get current user (protected)
- `GET /api/admin-test` - Admin test route (admin only)

### Problems
- `GET /api/problems` - Get all problems
- `GET /api/problems/:id` - Get problem by ID
- `POST /api/problems` - Create problem (admin only)
- `PUT /api/problems/:id` - Update problem (admin only)
- `DELETE /api/problems/:id` - Delete problem (admin only)

### Submissions
- `POST /api/submit` - Submit code for a problem
- `GET /api/submit/user` - Get user's submissions

### AI Review
- `POST /api/ai-review` - Get AI review of code

## Project Structure

```
backend/
├── src/
│   └── main/
│       ├── java/com/compilerai/backend/
│       │   ├── BackendApplication.java
│       │   ├── controller/
│       │   │   ├── AuthController.java
│       │   │   ├── ProblemController.java
│       │   │   ├── SubmissionController.java
│       │   │   ├── AIReviewController.java
│       │   │   └── TestController.java
│       │   ├── service/
│       │   │   ├── AuthService.java
│       │   │   ├── ProblemService.java
│       │   │   ├── SubmissionService.java
│       │   │   ├── CompilerService.java
│       │   │   └── AIReviewService.java
│       │   ├── repository/
│       │   │   ├── UserRepository.java
│       │   │   ├── ProblemRepository.java
│       │   │   └── SubmissionRepository.java
│       │   ├── model/
│       │   │   ├── User.java
│       │   │   ├── Problem.java
│       │   │   ├── Submission.java
│       │   │   ├── TestCase.java
│       │   │   └── TestResult.java
│       │   ├── dto/
│       │   │   ├── RegisterRequest.java
│       │   │   ├── LoginRequest.java
│       │   │   ├── AuthResponse.java
│       │   │   ├── ProblemRequest.java
│       │   │   ├── SubmissionRequest.java
│       │   │   ├── AIReviewRequest.java
│       │   │   ├── AIReviewResponse.java
│       │   │   ├── CompilerRequest.java
│       │   │   └── CompilerResponse.java
│       │   └── security/
│       │       ├── SecurityConfig.java
│       │       ├── JwtUtil.java
│       │       └── JwtAuthenticationFilter.java
│       └── resources/
│           └── application.properties
├── pom.xml
├── Dockerfile
└── README-SPRING-BOOT.md
```

## Key Changes from Node.js

1. **Technology Stack**:
   - Express.js → Spring Boot
   - Mongoose → Spring Data MongoDB
   - bcrypt → BCryptPasswordEncoder
   - jsonwebtoken → JJWT
   - axios → WebClient

2. **Architecture**:
   - Controllers handle HTTP requests/responses
   - Services contain business logic
   - Repositories handle database operations
   - DTOs for request/response objects
   - Models for database entities

3. **Security**:
   - Spring Security with JWT
   - Role-based access control with `@PreAuthorize`
   - CORS configuration

4. **Dependencies**:
   - Maven for dependency management
   - Java 17 runtime

## Testing

```bash
# Run tests
mvn test

# Run with coverage
mvn test jacoco:report
```

## Building for Production

```bash
# Build optimized JAR
mvn clean package -DskipTests

# The JAR will be in target/backend-1.0.0.jar
```

## Troubleshooting

### MongoDB Connection Issues
- Ensure MongoDB is running
- Check MONGO_URI in .env file
- Verify network connectivity

### Port Already in Use
- Change PORT in .env file
- Or kill the process using port 5050

### JWT Token Issues
- Ensure JWT_SECRET is set in .env
- Secret should be at least 256 bits (32 characters) for HS256

## Old Node.js Files

The following old Node.js files can be removed after verifying the Spring Boot version works:
- `server.js`
- `package.json`
- `package-lock.json`
- `rough.txt`
- `config/`
- `controllers/`
- `middleware/`
- `models/`
- `routes/`
