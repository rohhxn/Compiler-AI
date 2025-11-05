# 🚀 Quick GitHub Deployment Setup

This guide will get you deployed in **under 10 minutes**!

## Prerequisites Checklist
- [ ] MongoDB connection string
- [ ] JWT secret key (min 32 chars)
- [ ] Google Gemini API key
- [ ] GitHub account

## Quick Start: Deploy to Render (Free)

### Step 1: Add Secrets to GitHub (2 minutes)

1. Go to: https://github.com/rohhxn/Compiler-AI/settings/secrets/actions
2. Click **"New repository secret"** and add these **3 secrets**:

| Secret Name | Value | Where to Get |
|------------|-------|--------------|
| `MONGO_URI` | Your MongoDB connection string | [MongoDB Atlas Free](https://www.mongodb.com/cloud/atlas/register) |
| `JWT_SECRET` | Random 32+ char string | Use: `openssl rand -base64 32` or any long password |
| `GEMINI_API_KEY` | Your Gemini API key | [Get Free Key](https://makersuite.google.com/app/apikey) |

### Step 2: Deploy Backend on Render (3 minutes)

1. Go to: https://dashboard.render.com/register
2. Sign up with GitHub
3. Click **"New +"** → **"Web Service"**
4. Select your **"Compiler-AI"** repository
5. Fill in:
   ```
   Name: compiler-ai-backend
   Environment: Docker
   Branch: main
   Root Directory: backend
   ```
6. Click **"Advanced"** and add these Environment Variables:
   ```
   MONGO_URI = [paste from GitHub secret]
   JWT_SECRET = [paste from GitHub secret]
   GEMINI_API_KEY = [paste from GitHub secret]
   PORT = 8080
   ```
7. Click **"Create Web Service"** ✅
8. **Copy the URL** (e.g., `https://compiler-ai-backend.onrender.com`)

### Step 3: Deploy Compiler Service (2 minutes)

1. Click **"New +"** → **"Web Service"**
2. Select repository again
3. Fill in:
   ```
   Name: compiler-ai-compiler
   Environment: Docker
   Branch: main
   Root Directory: compiler
   ```
4. Click **"Create Web Service"** ✅

### Step 4: Deploy Frontend on Vercel (3 minutes)

1. Go to: https://vercel.com/signup
2. Sign up with GitHub
3. Click **"Add New..."** → **"Project"**
4. Import **"Compiler-AI"**
5. Configure:
   ```
   Framework Preset: Vite
   Root Directory: frontend
   Build Command: npm run build
   Output Directory: dist
   ```
6. Add Environment Variable:
   ```
   Name: VITE_API_URL
   Value: [Your backend URL from Step 2]
   ```
7. Click **"Deploy"** ✅
8. Wait 2 minutes for build
9. **Your app is live!** 🎉

## Alternative: One-Click Deploy to Railway

1. Go to: https://railway.app?referralCode=bonus
2. Click **"Start a New Project"** → **"Deploy from GitHub repo"**
3. Select **"Compiler-AI"**
4. Railway will automatically:
   - Detect all services (backend, compiler, frontend)
   - Create deployment configs
   - Provide URLs
5. Add environment variables to backend service:
   - `MONGO_URI`
   - `JWT_SECRET`
   - `GEMINI_API_KEY`
6. Done! Railway handles everything ✅

## Test Your Deployment

### Backend Health Check:
```bash
curl https://your-backend-url.onrender.com/
# Expected: "Backend is running"

curl https://your-backend-url.onrender.com/api/test
# Expected: "Test endpoint working"
```

### Frontend:
Visit: `https://your-frontend-url.vercel.app`

### Complete Flow:
1. ✅ Open frontend URL
2. ✅ Register new account
3. ✅ Login
4. ✅ Browse problems
5. ✅ Submit code
6. ✅ Get AI review

## Automatic Deployments

Once set up, any push to `main` branch will:
- ✅ Run tests
- ✅ Build Docker images
- ✅ Deploy automatically
- ✅ Run health checks

Check deployment status: https://github.com/rohhxn/Compiler-AI/actions

## Free Tier Limits

| Service | Free Tier | Enough For |
|---------|-----------|------------|
| Render | 750 hrs/month | Personal projects |
| Vercel | Unlimited deploys | Personal projects |
| MongoDB Atlas | 512MB storage | Thousands of users |
| Railway | $5 credit/month | Testing & development |

## Upgrade Options (Optional)

If you exceed free tier:
- **Render Starter:** $7/month (25GB bandwidth)
- **Vercel Pro:** $20/month (100GB bandwidth)
- **MongoDB M10:** $9/month (2GB-10GB storage)

## Need Help?

### Common Issues:

**❌ Build fails:**
- Check GitHub Actions logs
- Verify Java 17 is used
- Check Maven dependencies

**❌ Backend won't start:**
- Verify `MONGO_URI` is correct
- Check MongoDB Atlas IP whitelist (allow all: `0.0.0.0/0`)
- Verify JWT_SECRET is 32+ characters

**❌ Frontend shows errors:**
- Check `VITE_API_URL` points to backend
- Verify backend is running
- Check browser console for CORS errors

**❌ CORS errors:**
- Update `SecurityConfig.java` with frontend URL
- Redeploy backend

### Get Support:
- Open issue: https://github.com/rohhxn/Compiler-AI/issues
- Check logs on platform dashboard
- Review deployment documentation

## What's Next?

- [ ] Set up custom domain
- [ ] Configure email notifications
- [ ] Add monitoring (Sentry, LogRocket)
- [ ] Set up database backups
- [ ] Add more problems to platform
- [ ] Share with friends! 🚀

---

**Deployment Time:** ~10 minutes  
**Cost:** $0 (Free tier)  
**Maintenance:** Auto-deploys on push  

🎉 **You're now live!** Share your project: `https://your-app.vercel.app`
