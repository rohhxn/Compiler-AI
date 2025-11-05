# ✅ Deployment Checklist

## 🎯 Your Mission: Deploy in 10 Minutes!

### Step 1: Add GitHub Secrets (2 min) ⏰
**Go to:** https://github.com/rohhxn/Compiler-AI/settings/secrets/actions

Click **"New repository secret"** and add these 3 secrets:

- [ ] **MONGO_URI**
  - Get free database: https://www.mongodb.com/cloud/atlas/register
  - Create cluster → Connect → Get connection string
  - Replace `<password>` with your actual password
  - Example: `mongodb+srv://user:pass@cluster.mongodb.net/compiler-ai`

- [ ] **JWT_SECRET**
  - Generate: `openssl rand -base64 32` (in terminal)
  - Or use any long random string (32+ characters)
  - Example: `7h1s-1s-4-v3ry-l0ng-s3cr3t-k3y-f0r-jwt-t0k3ns-123456789`

- [ ] **GEMINI_API_KEY**
  - Get free key: https://makersuite.google.com/app/apikey
  - Click "Create API Key"
  - Copy the key

### Step 2: Deploy Backend (3 min) ⏰
**Go to:** https://dashboard.render.com/register

- [ ] Sign up with GitHub
- [ ] Click "New +" → "Web Service"
- [ ] Select "Compiler-AI" repository
- [ ] Configure:
  ```
  Name: compiler-ai-backend
  Environment: Docker
  Branch: main
  Root Directory: backend
  ```
- [ ] Add Environment Variables:
  ```
  MONGO_URI = [your MongoDB URI]
  JWT_SECRET = [your JWT secret]
  GEMINI_API_KEY = [your Gemini key]
  PORT = 8080
  ```
- [ ] Click "Create Web Service"
- [ ] **COPY THE URL** (you'll need it for frontend!)

### Step 3: Deploy Compiler (2 min) ⏰
Still on Render:

- [ ] Click "New +" → "Web Service"
- [ ] Select "Compiler-AI" repository
- [ ] Configure:
  ```
  Name: compiler-ai-compiler
  Environment: Docker
  Branch: main
  Root Directory: compiler
  ```
- [ ] Click "Create Web Service"

### Step 4: Deploy Frontend (3 min) ⏰
**Go to:** https://vercel.com/signup

- [ ] Sign up with GitHub
- [ ] Click "Add New..." → "Project"
- [ ] Import "Compiler-AI"
- [ ] Configure:
  ```
  Framework: Vite
  Root Directory: frontend
  Build Command: npm run build
  Output Directory: dist
  ```
- [ ] Add Environment Variable:
  ```
  VITE_API_URL = [Your backend URL from Step 2]
  ```
- [ ] Click "Deploy"
- [ ] Wait 2 minutes
- [ ] **YOUR APP IS LIVE!** 🎉

### Step 5: Test Your Deployment (2 min) ⏰

- [ ] Open your Vercel URL (e.g., `https://compiler-ai.vercel.app`)
- [ ] Try to register a new account
- [ ] Login with credentials
- [ ] Browse problems
- [ ] Submit a test solution
- [ ] Check if AI review works

### Step 6: Optional - Add More Secrets for Auto-Deploy

For automatic deployments on push, add these optional secrets:

**Go to:** https://github.com/rohhxn/Compiler-AI/settings/secrets/actions

- [ ] **RENDER_DEPLOY_HOOK_URL** (optional)
  - Get from: Render Dashboard → Your Service → Settings → Deploy Hook
  
- [ ] **BACKEND_URL** (optional)
  - Your backend URL: `https://compiler-ai-backend.onrender.com`
  
- [ ] **FRONTEND_URL** (optional)
  - Your Vercel URL: `https://compiler-ai.vercel.app`

- [ ] **DOCKER_USERNAME** (optional - for Docker Hub)
  - Your Docker Hub username
  
- [ ] **DOCKER_PASSWORD** (optional - for Docker Hub)
  - Your Docker Hub password/token

## 🎉 Success Indicators

### ✅ Backend Working:
```bash
curl https://your-backend-url.onrender.com/
# Should return: "Backend is running"
```

### ✅ Frontend Working:
- Page loads
- Can see login/register forms
- No console errors

### ✅ Full Integration Working:
- Can register/login
- Can view problems
- Can submit code
- AI review generates feedback

## 📊 Monitor Your Deployment

### Check Deployment Status:
- GitHub Actions: https://github.com/rohhxn/Compiler-AI/actions
- Render Dashboard: https://dashboard.render.com
- Vercel Dashboard: https://vercel.com/dashboard

### View Logs:
- **Backend Logs:** Render Dashboard → backend service → Logs
- **Frontend Logs:** Vercel Dashboard → Project → Function Logs

## 🚨 Troubleshooting

### Backend won't start:
1. Check Render logs for errors
2. Verify MongoDB URI is correct
3. Ensure MongoDB Atlas allows connections from anywhere (0.0.0.0/0)
4. Check JWT_SECRET is at least 32 characters

### Frontend can't connect:
1. Verify `VITE_API_URL` is correct
2. Check if backend is running
3. Look for CORS errors in browser console
4. Ensure backend URL ends without trailing slash

### Build fails:
1. Check GitHub Actions logs
2. Verify all files committed
3. Check Render/Vercel build logs

## 📈 Usage & Limits

### Free Tier:
- **Render:** 750 hours/month (enough for 24/7 operation)
- **Vercel:** Unlimited deployments
- **MongoDB Atlas:** 512MB storage
- **Gemini API:** Free tier available

### If You Need More:
- Scale up on Render: $7/month per service
- MongoDB M10: $9/month for 2GB
- Vercel Pro: $20/month for commercial use

## 🎯 Next Actions

After successful deployment:
- [ ] Share your app with friends
- [ ] Add custom domain (optional)
- [ ] Set up monitoring (optional)
- [ ] Add more coding problems
- [ ] Create documentation for users
- [ ] Submit to Product Hunt/Show HN

## 🆘 Need Help?

- **Issue?** Open: https://github.com/rohhxn/Compiler-AI/issues
- **Questions?** Check documentation in repo
- **Stuck?** Review logs in platform dashboards

---

## 📝 Quick Reference

### Your URLs (fill these in):
- **Backend:** `https://_________________.onrender.com`
- **Frontend:** `https://_________________.vercel.app`
- **GitHub:** `https://github.com/rohhxn/Compiler-AI`

### Important Links:
- MongoDB Atlas: https://cloud.mongodb.com
- Render Dashboard: https://dashboard.render.com
- Vercel Dashboard: https://vercel.com/dashboard
- GitHub Secrets: https://github.com/rohhxn/Compiler-AI/settings/secrets/actions
- GitHub Actions: https://github.com/rohhxn/Compiler-AI/actions

---

**Total Time:** ~10 minutes  
**Total Cost:** $0 (Free tier)  

🚀 **Ready? Start with Step 1 above!**
