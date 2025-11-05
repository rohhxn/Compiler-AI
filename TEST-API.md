# 🧪 API Testing Guide

## Test Your Backend API Directly

### ✅ 1. Test Backend Health (Should work immediately)
```bash
curl https://compiler-ai-backend.onrender.com/
```
**Expected**: `"API is working 🚀"`

---

### ✅ 2. Test Registration (After CORS fix deploys)

**Open Browser DevTools** (F12) → **Console** tab → Paste this:

```javascript
fetch('https://compiler-ai-backend.onrender.com/api/register', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({
    name: 'Test User',
    email: 'test@example.com',
    password: 'testpass123',
    role: 'user'
  })
})
.then(res => res.json())
.then(data => console.log('✅ Success:', data))
.catch(err => console.error('❌ Error:', err));
```

**Expected**: `{ message: "User registered successfully!" }`

---

### ✅ 3. Test Login

```javascript
fetch('https://compiler-ai-backend.onrender.com/api/login', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({
    email: 'test@example.com',
    password: 'testpass123'
  })
})
.then(res => res.json())
.then(data => console.log('✅ Login Success:', data))
.catch(err => console.error('❌ Error:', err));
```

**Expected**: 
```json
{
  "token": "eyJhbGc...",
  "userId": "...",
  "role": "user",
  "message": "Login successful!"
}
```

---

## 🔍 Common Issues & Solutions

### Issue: CORS Error
**Symptom**: `Access to fetch at 'https://compiler-ai-backend...' from origin 'https://compiler-ai-flame.vercel.app' has been blocked by CORS policy`

**Solution**: 
- Wait for Render to redeploy with the new CORS settings (2-3 minutes)
- Check Render dashboard → Your service → "Live" status

### Issue: "Registration failed" with no details
**Check**:
1. Open Browser DevTools (F12)
2. Go to **Console** tab - look for errors
3. Go to **Network** tab → Click the failed request → Check "Response"

### Issue: 500 Internal Server Error
**Check Render Logs**:
1. Go to Render dashboard
2. Click your backend service
3. Click "Logs" tab
4. Look for the error message

---

## 📋 Checklist After CORS Fix Deploys

- [ ] Backend shows "Live" status on Render
- [ ] Test 1: Health check works
- [ ] Test 2: Registration works
- [ ] Test 3: Login works
- [ ] Frontend registration form works
- [ ] Frontend login form works

---

## 🚀 Next Steps After Tests Pass

1. **Test Compiler Service**: Submit code through frontend
2. **Test AI Review**: Check if Gemini API integration works
3. **Test Admin Features**: Create admin user and test problem creation
4. **Performance**: Check response times (first request may be slow due to cold start)

---

## 📞 If You Need Help

Share:
1. **Browser Console errors** (F12 → Console tab)
2. **Network request details** (F12 → Network tab → Click request → Response)
3. **Render logs** (From backend service logs tab)
