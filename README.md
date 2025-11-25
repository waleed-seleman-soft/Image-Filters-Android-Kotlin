# 📸 Image Filters App (Android – Kotlin)

A modern Android app that allows users to choose an image and apply real-time GPU filters — similar to Instagram.  
Built with **Kotlin**, **GLSurfaceView**, **RecyclerView**, and the **PhotoFilter** library.

---

## ✨ Features

- 🎨 **25+ real-time image filters** (Brightness, Contrast, Sepia, Vignette, Grain, etc.)
- 🖼 **Select images from gallery**
- ⚡ **Instant live filter preview using OpenGL**
- 🔁 **Crash-free bitmap handling**
- 📷 **Default image shown when no picture is selected**
- 📱 **Clean, simple, responsive UI**

---

## 🛠 Tech Stack

- Kotlin  
- AndroidX  
- GLSurfaceView  
- RecyclerView  
- Mukesh PhotoFilter Library  
- Activity Result API (modern image picker)

---

## 🚀 How It Works

### 1️⃣ Load Default Image  
App loads a built-in background image and displays it with a “None” filter.

### 2️⃣ Choose Image  
User selects an image from the gallery using the latest Android Activity Result API.

### 3️⃣ Apply Filters  
Each filter tap produces a *safe working bitmap* to prevent OpenGL crashes.

### 4️⃣ GPU Rendering  
The PhotoFilter library applies effects using OpenGL shaders in real-time.

---

## 📱 Screenshots
![Screenshot 1](screenshots/image1.png)
![Screenshot 2](screenshots/image2.png)
