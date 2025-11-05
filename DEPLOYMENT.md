# 🚀 GitHub Deployment Guide

## Step-by-Step Guide to Deploy to GitHub

### 1. Prepare Your Repository

First, make sure sensitive files are not committed:

```bash
# Check what will be committed
git status

# Make sure .env files are in .gitignore
cat backend/.gitignore | grep .env
```

### 2. Add All New Files

```bash
# Add all the new Spring Boot files
git add backend/src/
git add backend/pom.xml
git add backend/README-SPRING-BOOT.md
git add backend/QUICKSTART.md
git add backend/.env.example
git add backend/Dockerfile
git add backend/start.bat
git add backend/start.sh
git add backend/mvnw

# Add project documentation
git add README.md
git add MIGRATION-GUIDE.md

# Add GitHub Actions workflow
git add .github/

# Add gitignore files
git add .gitignore
git add backend/.gitignore
```

### 3. Commit Your Changes

```bash
git commit -m "feat: Migrate backend from Node.js to Spring Boot

- Converted Express.js to Spring Boot 3.2.0
- Migrated Mongoose schemas to Spring Data MongoDB entities
- Implemented Spring Security with JWT authentication
- Created service layer for business logic
- Added comprehensive documentation
- Updated Dockerfile for Java/Maven build
- Added GitHub Actions CI/CD workflow
- Maintained 100% API compatibility with frontend
"
```

### 4. Push to GitHub

```bash
# Push to main branch
git push origin main

# Or if you want to create a new branch first
git checkout -b feature/spring-boot-migration
git push origin feature/spring-boot-migration
```

## 🔐 GitHub Secrets Setup

For CI/CD to work properly, you need to add these secrets to your GitHub repository:

### Navigate to GitHub Secrets:
1. Go to your repository on GitHub
2. Click on **Settings** > **Secrets and variables** > **Actions**
3. Click **New repository secret**

### Add These Secrets:

| Secret Name | Description | Example |
|-------------|-------------|---------|
| `MONGO_URI` | MongoDB connection string | `mongodb+srv://user:pass@cluster.mongodb.net/db` |
| `JWT_SECRET` | JWT secret key (32+ chars) | `your-secret-key-min-32-characters` |
| `GEMINI_API_KEY` | Google Gemini API key | `AIzaSy...` |
| `DOCKER_USERNAME` | Docker Hub username | `yourusername` |
| `DOCKER_PASSWORD` | Docker Hub password/token | `dckr_pat_...` |

## 🌐 Deployment Options

### Option 1: Deploy to Heroku

1. **Install Heroku CLI**
2. **Create Heroku app**
   ```bash
   heroku create compiler-ai-backend
   ```
3. **Add buildpack**
   ```bash
   heroku buildpacks:set heroku/java
   ```
4. **Set environment variables**
   ```bash
   heroku config:set MONGO_URI="your-mongo-uri"
   heroku config:set JWT_SECRET="your-jwt-secret"
   heroku config:set GEMINI_API_KEY="your-api-key"
   ```
5. **Deploy**
   ```bash
   git push heroku main
   ```

### Option 2: Deploy to AWS EC2

1. **Launch EC2 instance** (Ubuntu 22.04)
2. **Install Docker and Docker Compose**
   ```bash
   sudo apt update
   sudo apt install docker.io docker-compose
   ```
3. **Clone repository**
   ```bash
   git clone https://github.com/rohhxn/Compiler-AI.git
   cd Compiler-AI
   ```
4. **Create .env file**
   ```bash
   cp backend/.env.example backend/.env
   nano backend/.env  # Add your values
   ```
5. **Run with Docker Compose**
   ```bash
   docker-compose up -d
   ```

### Option 3: Deploy to Azure

1. **Create Azure Container Registry**
2. **Build and push images**
   ```bash
   docker build -t myregistry.azurecr.io/backend:latest ./backend
   docker push myregistry.azurecr.io/backend:latest
   ```
3. **Create Azure Container Instances**
4. **Set environment variables in Azure Portal**

### Option 4: Deploy to Google Cloud Run

1. **Build container**
   ```bash
   gcloud builds submit --tag gcr.io/PROJECT-ID/backend
   ```
2. **Deploy to Cloud Run**
   ```bash
   gcloud run deploy backend \
     --image gcr.io/PROJECT-ID/backend \
     --platform managed \
     --region us-central1 \
     --allow-unauthenticated
   ```
3. **Set environment variables**
   ```bash
   gcloud run services update backend \
     --set-env-vars MONGO_URI=xxx,JWT_SECRET=xxx
   ```

## 📦 Docker Hub Deployment

### 1. Build Image

```bash
cd backend
docker build -t yourusername/compiler-ai-backend:latest .
```

### 2. Login to Docker Hub

```bash
docker login
```

### 3. Push Image

```bash
docker push yourusername/compiler-ai-backend:latest
```

### 4. Others Can Pull and Run

```bash
docker pull yourusername/compiler-ai-backend:latest
docker run -p 5050:5050 --env-file .env yourusername/compiler-ai-backend:latest
```

## 🔄 Continuous Deployment

The GitHub Actions workflow automatically:

1. ✅ Runs tests on every push
2. ✅ Builds Docker image
3. ✅ Checks code quality
4. ✅ Creates artifacts

To enable auto-deployment, update `.github/workflows/backend.yml`:

```yaml
# Add this job at the end
deploy:
  needs: [build-and-test]
  runs-on: ubuntu-latest
  if: github.ref == 'refs/heads/main'
  
  steps:
  - name: Deploy to production
    run: |
      # Add your deployment commands here
      echo "Deploying to production..."
```

## 🧪 Testing Deployment

After deployment, test your endpoints:

```bash
# Health check
curl https://your-domain.com/

# Test register
curl -X POST https://your-domain.com/api/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Test","email":"test@test.com","password":"test123"}'

# Test login
curl -X POST https://your-domain.com/api/login \
  -H "Content-Type: application/json" \
  -d '{"email":"test@test.com","password":"test123"}'
```

## 🐛 Troubleshooting Deployment

### Issue: Build fails on GitHub Actions

**Solution:** Check that all dependencies are in `pom.xml`

### Issue: MongoDB connection fails

**Solution:** 
- Use MongoDB Atlas for cloud database
- Add IP whitelist in MongoDB Atlas
- Check connection string format

### Issue: Port already in use

**Solution:** Change PORT in environment variables

### Issue: Docker build fails

**Solution:** 
- Check Dockerfile syntax
- Ensure all paths are correct
- Run `docker system prune` to clean up

## 📊 Monitoring

### Add Health Check Endpoint

Spring Boot Actuator is included. Access:
- Health: `/actuator/health`
- Metrics: `/actuator/metrics`
- Info: `/actuator/info`

### Logging

View logs:
```bash
# Docker logs
docker logs -f compiler-ai-backend

# Heroku logs
heroku logs --tail

# Cloud platforms: Check their logging services
```

## 🎯 Production Checklist

Before going to production:

- [ ] Change JWT_SECRET to a strong random value
- [ ] Set up proper MongoDB authentication
- [ ] Enable HTTPS/SSL
- [ ] Configure CORS for your domain only
- [ ] Set up monitoring and alerts
- [ ] Configure backup strategy for database
- [ ] Set up rate limiting
- [ ] Review and restrict API access
- [ ] Enable logging and log aggregation
- [ ] Set up error tracking (Sentry, etc.)
- [ ] Configure auto-scaling if needed
- [ ] Set up CDN for static assets
- [ ] Document your deployment process

## 🌟 Next Steps

1. Push your code to GitHub
2. Set up GitHub Secrets
3. Watch GitHub Actions build your app
4. Choose a deployment platform
5. Deploy and test
6. Monitor and iterate

---

**Need help?** Open an issue on GitHub or check the documentation!
