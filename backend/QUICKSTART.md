# 🚀 QUICK START GUIDE

## Step 1: Create .env File

Create a file named `.env` in the `backend` folder with this content:

```env
MONGO_URI=mongodb://localhost:27017/compiler-ai
JWT_SECRET=your-super-secret-jwt-key-must-be-at-least-32-characters-long-for-security
COMPILER_URL=http://localhost:8000
GEMINI_API_KEY=your-gemini-api-key-here
PORT=5050
```

**Important:** Replace `your-gemini-api-key-here` with your actual Gemini API key!

## Step 2: Install Maven (One-time Setup)

Since Maven isn't installed, you have two options:

### Option A: Install Maven (Recommended)
1. Download Maven from: https://maven.apache.org/download.cgi
2. Extract to `C:\Program Files\Apache\maven`
3. Add to PATH: `C:\Program Files\Apache\maven\bin`
4. Restart PowerShell/Command Prompt

### Option B: Use Docker (Skip Maven entirely)
See "Using Docker" section below.

## Step 3: Run the Application

### Method 1: Using Maven (After installing)
```powershell
cd backend
mvn spring-boot:run
```

### Method 2: Build JAR and Run
```powershell
cd backend

# Build (first time only, or after code changes)
mvn clean package -DskipTests

# Run
java -jar target/backend-1.0.0.jar
```

### Method 3: Using Docker (No Maven needed!)
```powershell
# From backend directory
docker build -t compiler-ai-backend .
docker run -p 5050:5050 --env-file .env compiler-ai-backend
```

### Method 4: Using Docker Compose (Easiest for full stack!)
```powershell
# From project root (Compiler-AI folder)
docker-compose up --build
```

## Step 4: Verify It's Running

Open browser to: http://localhost:5050

You should see: "API is working 🚀"

### Test Login Endpoint
```powershell
# Using curl (if installed)
curl http://localhost:5050/test

# Or use Postman/Thunder Client
POST http://localhost:5050/api/register
```

## 🐛 Troubleshooting

### Error: MongoDB connection failed
**Solution:** Make sure MongoDB is running
```powershell
# Check if MongoDB service is running
Get-Service -Name MongoDB*

# Or start MongoDB manually
mongod
```

### Error: Port 5050 already in use
**Solution:** Change port in .env file or kill the process
```powershell
# Find what's using port 5050
netstat -ano | findstr :5050

# Kill the process (replace PID with actual number)
taskkill /PID <PID> /F
```

### Error: Cannot find or load main class
**Solution:** Rebuild the project
```powershell
mvn clean package -DskipTests
```

## 📋 Environment Variables Explained

| Variable | Description | Example |
|----------|-------------|---------|
| `MONGO_URI` | MongoDB connection string | `mongodb://localhost:27017/compiler-ai` |
| `JWT_SECRET` | Secret key for JWT tokens (32+ chars) | `your-secret-key-here-min-32-chars` |
| `COMPILER_URL` | URL of code compiler service | `http://localhost:8000` |
| `GEMINI_API_KEY` | Google Gemini API key for AI review | Get from Google AI Studio |
| `PORT` | Port for backend server | `5050` |

## 🎯 Recommended: Docker Compose (All Services)

This starts everything (frontend, backend, compiler):

```powershell
# From project root
docker-compose up --build

# To run in background
docker-compose up -d --build

# To stop
docker-compose down
```

## 📦 First Time Setup Summary

1. **Create `.env` file** in backend folder ✓
2. **Install Maven** OR use Docker ✓
3. **Start MongoDB** (if not using Docker) ✓
4. **Run the application** using one of the methods above ✓
5. **Test** by visiting http://localhost:5050 ✓

## 🎉 You're Ready!

Once the application starts, you'll see:
```
🚀 Compiler-AI Backend is running!
```

The backend will be available at: **http://localhost:5050**

---

**Need help?** Check MIGRATION-GUIDE.md for detailed documentation.
