# 🚀 Compiler-AI - Online Code Compiler with AI Review

A full-stack online coding platform with an AI-powered code review system built with Spring Boot, React, and Docker.

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![React](https://img.shields.io/badge/React-18-61DAFB)
![MongoDB](https://img.shields.io/badge/MongoDB-Latest-green)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED)

## ✨ Features

- **Multi-Language Code Execution**: Support for C, C++, Java, Python, and JavaScript
- **Problem Management**: Create, edit, and manage coding problems (Admin)
- **Test Case Validation**: Automatic validation against multiple test cases
- **AI Code Review**: Get intelligent code feedback using Google Gemini AI
- **User Authentication**: JWT-based secure authentication
- **Role-Based Access**: User and Admin roles with different permissions
- **Submission History**: Track all code submissions and results
- **Docker Support**: Easy deployment with Docker and Docker Compose

## 🏗️ Architecture

```
┌─────────────┐      ┌─────────────┐      ┌─────────────┐
│   Frontend  │──────│   Backend   │──────│  Compiler   │
│   (React)   │      │ (Spring Boot)│      │  Service    │
└─────────────┘      └─────────────┘      └─────────────┘
                            │
                            │
                     ┌──────▼──────┐
                     │   MongoDB   │
                     └─────────────┘
```

## 🛠️ Tech Stack

### Backend (Spring Boot)
- **Framework**: Spring Boot 3.2.0
- **Language**: Java 17
- **Database**: MongoDB with Spring Data
- **Security**: Spring Security + JWT
- **API**: RESTful APIs
- **Build Tool**: Maven
- **AI Integration**: Google Gemini AI

### Frontend
- **Framework**: React 18
- **Styling**: Tailwind CSS
- **HTTP Client**: Axios
- **Routing**: React Router

### Compiler Service
- **Runtime**: Node.js
- **Execution**: Isolated code execution environment

### Infrastructure
- **Containerization**: Docker
- **Orchestration**: Docker Compose
- **CI/CD**: GitHub Actions

## 📋 Prerequisites

- **Java 17+** (for local development)
- **Maven 3.6+** (or use included Maven wrapper)
- **MongoDB** (or use Docker)
- **Node.js 18+** (for frontend and compiler)
- **Docker & Docker Compose** (recommended)

## 🚀 Quick Start

### Option 1: Using Docker Compose (Recommended)

1. **Clone the repository**
   ```bash
   git clone https://github.com/rohhxn/Compiler-AI.git
   cd Compiler-AI
   ```

2. **Create environment file**
   ```bash
   cp backend/.env.example backend/.env
   # Edit backend/.env and add your API keys
   ```

3. **Start all services**
   ```bash
   docker-compose up --build
   ```

4. **Access the application**
   - Frontend: http://localhost:3001
   - Backend: http://localhost:5050
   - Compiler: http://localhost:8000

### Option 2: Local Development

#### Backend
```bash
cd backend

# Create .env file
cp .env.example .env
# Edit .env with your configuration

# Run with Maven
mvn spring-boot:run

# Or build and run JAR
mvn clean package
java -jar target/backend-1.0.0.jar
```

#### Frontend
```bash
cd frontend
npm install
npm run dev
```

#### Compiler Service
```bash
cd compiler
npm install
npm start
```

## 🔐 Environment Variables

Create a `.env` file in the `backend` directory:

```env
# MongoDB
MONGO_URI=mongodb://localhost:27017/compiler-ai

# JWT Secret (minimum 32 characters)
JWT_SECRET=your-super-secret-jwt-key-must-be-at-least-32-characters-long

# Compiler Service
COMPILER_URL=http://localhost:8000

# Google Gemini AI
GEMINI_API_KEY=your-gemini-api-key-here

# Server Port
PORT=5050
```

## 📡 API Endpoints

### Authentication
- `POST /api/register` - Register new user
- `POST /api/login` - User login
- `GET /api/me` - Get current user (Protected)

### Problems
- `GET /api/problems` - List all problems
- `GET /api/problems/:id` - Get problem details
- `POST /api/problems` - Create problem (Admin only)
- `PUT /api/problems/:id` - Update problem (Admin only)
- `DELETE /api/problems/:id` - Delete problem (Admin only)

### Submissions
- `POST /api/submit` - Submit code solution
- `GET /api/submit/user` - Get user's submissions

### AI Review
- `POST /api/ai-review` - Get AI code review

## 🧪 Testing

```bash
# Backend tests
cd backend
mvn test

# Frontend tests
cd frontend
npm test
```

## 📦 Project Structure

```
Compiler-AI/
├── backend/                    # Spring Boot Backend
│   ├── src/
│   │   └── main/
│   │       ├── java/com/compilerai/backend/
│   │       │   ├── controller/     # REST Controllers
│   │       │   ├── service/        # Business Logic
│   │       │   ├── repository/     # Data Access
│   │       │   ├── model/          # Entities
│   │       │   ├── dto/            # Data Transfer Objects
│   │       │   └── security/       # Security Config
│   │       └── resources/
│   │           └── application.properties
│   ├── pom.xml
│   └── Dockerfile
│
├── frontend/                   # React Frontend
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   └── routes/
│   └── Dockerfile
│
├── compiler/                   # Code Execution Service
│   ├── index.js
│   ├── runCode.js
│   └── Dockerfile
│
├── docker-compose.yml
└── .github/
    └── workflows/              # CI/CD Pipelines
```

## 🔄 Migration from Node.js

This project was migrated from Node.js/Express to Spring Boot. See [MIGRATION-GUIDE.md](MIGRATION-GUIDE.md) for details.

## 🚢 Deployment

### GitHub Actions

The project includes CI/CD workflows for automated testing and building:

- **Backend Pipeline**: Builds, tests, and creates Docker image
- **Frontend Pipeline**: Builds and deploys frontend
- **Auto-deployment**: On push to main branch

### Manual Deployment

1. **Build Docker images**
   ```bash
   docker-compose build
   ```

2. **Push to registry**
   ```bash
   docker tag compiler-ai-backend:latest your-registry/backend:latest
   docker push your-registry/backend:latest
   ```

3. **Deploy to server**
   ```bash
   docker-compose up -d
   ```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 API Documentation

For detailed API documentation, see [API.md](docs/API.md) (coming soon)

Or access Swagger UI at: http://localhost:5050/swagger-ui.html (when running)

## 🐛 Known Issues

- AI Review requires valid Gemini API key
- Code execution timeout is set to 5 seconds
- MongoDB must be accessible from backend

## 🎯 Roadmap

- [ ] Add Swagger/OpenAPI documentation
- [ ] Implement rate limiting
- [ ] Add Redis caching
- [ ] Support more programming languages
- [ ] Add code playground mode
- [ ] Implement real-time collaboration
- [ ] Add leaderboard system
- [ ] Mobile responsive improvements

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **Rohan** - [rohhxn](https://github.com/rohhxn)

## 🙏 Acknowledgments

- Spring Boot team for the amazing framework
- Google for Gemini AI API
- Docker for containerization
- All contributors and users

## 📞 Support

For support, email your-email@example.com or open an issue on GitHub.

## 🌟 Star History

If you find this project useful, please consider giving it a star! ⭐

---

**Built with ❤️ using Spring Boot and React**
