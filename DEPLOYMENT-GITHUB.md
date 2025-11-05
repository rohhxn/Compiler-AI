# 🚀 GitHub Deployment Guide

## Prerequisites
- ✅ GitHub repository: https://github.com/rohhxn/Compiler-AI
- ✅ Spring Boot backend migrated
- ✅ Secret keys obtained

## Step 1: Add GitHub Secrets

Go to your repository settings and add these secrets:
**URL:** https://github.com/rohhxn/Compiler-AI/settings/secrets/actions

Click **"New repository secret"** for each:

### Required Secrets:

1. **`MONGO_URI`**
   - Name: `MONGO_URI`
   - Value: Your MongoDB connection string
   - Example: `mongodb+srv://username:password@cluster.mongodb.net/compiler-ai`
   - Get it from: [MongoDB Atlas](https://www.mongodb.com/cloud/atlas)

2. **`JWT_SECRET`**
   - Name: `JWT_SECRET`
   - Value: A strong random string (min 32 characters)
   - Example: Generate with: `openssl rand -base64 32`
   - Or use: `your-super-secret-jwt-key-min-32-chars-for-hs256-algorithm`

3. **`GEMINI_API_KEY`**
   - Name: `GEMINI_API_KEY`
   - Value: Your Google Gemini API key
   - Get it from: [Google AI Studio](https://makersuite.google.com/app/apikey)

### Optional Secrets (for deployment):

4. **`DOCKER_USERNAME`** (if deploying to Docker Hub)
   - Your Docker Hub username

5. **`DOCKER_PASSWORD`** (if deploying to Docker Hub)
   - Your Docker Hub password or access token

6. **`RENDER_API_KEY`** (if deploying to Render)
   - Get from: [Render Dashboard](https://dashboard.render.com/account/settings)

## Step 2: Choose Deployment Platform

### Option A: Deploy to Render (Recommended - Free Tier)

#### 2.1 Create Render Account
- Sign up at: https://render.com
- Connect your GitHub account

#### 2.2 Create MongoDB Database
1. Go to https://www.mongodb.com/cloud/atlas
2. Create a free cluster
3. Get connection string
4. Add to GitHub Secrets as `MONGO_URI`

#### 2.3 Deploy Backend on Render
1. Go to Render Dashboard
2. Click **"New +"** → **"Web Service"**
3. Connect your GitHub repository
4. Configure:
   - **Name:** `compiler-ai-backend`
   - **Environment:** `Docker`
   - **Region:** Choose closest to you
   - **Branch:** `main`
   - **Root Directory:** `backend`
   - **Docker Build Context:** `backend`
   - **Dockerfile Path:** `backend/Dockerfile`

5. Add Environment Variables:
   ```
   MONGO_URI=<your-mongodb-uri>
   JWT_SECRET=<your-jwt-secret>
   GEMINI_API_KEY=<your-gemini-key>
   COMPILER_URL=http://localhost:5000
   PORT=8080
   ```

6. Click **"Create Web Service"**

#### 2.4 Deploy Compiler Service
Repeat for compiler:
- **Name:** `compiler-ai-compiler`
- **Root Directory:** `compiler`
- **Port:** `5000`

#### 2.5 Deploy Frontend
1. **New** → **"Static Site"**
2. Configure:
   - **Name:** `compiler-ai-frontend`
   - **Build Command:** `npm run build`
   - **Publish Directory:** `dist`
   - **Root Directory:** `frontend`

3. Add Environment Variable:
   ```
   VITE_API_URL=https://compiler-ai-backend.onrender.com
   ```

### Option B: Deploy to Railway

1. Go to: https://railway.app
2. Click **"Start a New Project"**
3. Select **"Deploy from GitHub repo"**
4. Choose your repository
5. Add services:
   - Backend (from `backend/`)
   - Compiler (from `compiler/`)
   - Frontend (from `frontend/`)
6. Add environment variables for each service
7. Railway will auto-deploy on push

### Option C: Deploy to Vercel (Frontend) + Render (Backend)

#### Frontend on Vercel:
1. Go to: https://vercel.com
2. Import your repository
3. Configure:
   - **Framework:** Vite
   - **Root Directory:** `frontend`
   - **Build Command:** `npm run build`
   - **Output Directory:** `dist`
4. Add environment variable:
   ```
   VITE_API_URL=https://your-backend-url.onrender.com
   ```

#### Backend on Render:
- Follow "Option A" steps above

### Option D: Self-Host with Docker

If you have a VPS (AWS, DigitalOcean, etc.):

```bash
# SSH into your server
ssh user@your-server-ip

# Clone repository
git clone https://github.com/rohhxn/Compiler-AI.git
cd Compiler-AI

# Create .env files
nano backend/.env
# Paste your secrets

# Start with Docker Compose
docker-compose up -d

# Check status
docker-compose ps
docker-compose logs -f
```

## Step 3: Update GitHub Actions for Auto-Deploy

I'll create an enhanced workflow that automatically deploys to your chosen platform.

## Step 4: Test Deployment

Once deployed, test these endpoints:

### Backend Health Check:
```bash
curl https://your-backend-url.com/
# Should return: "Backend is running"

curl https://your-backend-url.com/api/test
# Should return: "Test endpoint working"
```

### Frontend:
- Visit: `https://your-frontend-url.com`
- Should load the application

### Full Flow Test:
1. Register a new user
2. Login
3. Browse problems
4. Submit a solution
5. Check AI review

## Step 5: Configure Custom Domain (Optional)

### For Render:
1. Go to your service settings
2. Click **"Custom Domains"**
3. Add your domain
4. Update DNS records

### For Vercel:
1. Go to project settings
2. Click **"Domains"**
3. Add custom domain
4. Follow DNS instructions

## Monitoring & Logs

### Render:
- View logs: Dashboard → Your Service → Logs
- Metrics: Dashboard → Your Service → Metrics

### Railway:
- View logs: Project → Service → Deployments → Logs

### Vercel:
- View logs: Project → Deployments → Function Logs

## Troubleshooting

### Build Fails:
- Check GitHub Actions logs
- Verify all dependencies are in `pom.xml`
- Ensure Java 17 is specified

### Runtime Errors:
- Check environment variables are set
- Verify MongoDB connection string
- Check service logs for errors

### CORS Issues:
- Update `SecurityConfig.java` with your frontend URL
- Add to allowed origins

## Cost Estimates

### Free Tier Options:
- **Render:** 750 hours/month free (multiple services)
- **Railway:** $5 free credit monthly
- **Vercel:** Unlimited for personal projects
- **MongoDB Atlas:** 512MB free forever

### Paid Options (if needed):
- **Render Starter:** $7/month per service
- **Railway Pro:** $5/month + usage
- **MongoDB Atlas:** $9/month for 2GB

## Next Steps

1. ✅ Add secrets to GitHub
2. ⏳ Choose deployment platform
3. ⏳ Deploy services
4. ⏳ Test endpoints
5. ⏳ Configure domain (optional)
6. ⏳ Set up monitoring

## Support

- **GitHub Issues:** https://github.com/rohhxn/Compiler-AI/issues
- **Documentation:** Check README files in each directory
- **Community:** Open discussions on GitHub

---

**Ready to deploy?** Start with Step 1 above! 🚀
