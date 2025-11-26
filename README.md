# Image Filters App (Android – Kotlin)

A modern Android app that allows users to choose an image and apply real-time GPU filters — similar to Instagram.  
Built with **Kotlin**, **GLSurfaceView**, **RecyclerView**, and the **PhotoFilter** library.

---

## Features

- **25+ real-time image filters** (Brightness, Contrast, Sepia, Vignette, Grain, etc.)
- **Select images from gallery**
- **Instant live filter preview using OpenGL**
- **Crash-free bitmap handling**
- **Default image shown when no picture is selected**
- **Clean, simple, responsive UI**

---

## Tech Stack

- Kotlin  
- AndroidX  
- GLSurfaceView  
- RecyclerView  
- Mukesh PhotoFilter Library  
- Activity Result API (modern image picker)

---

## How It Works

### 1️: Load Default Image  
App loads a built-in background image and displays it with a “None” filter.

### 2️: Choose Image  
User selects an image from the gallery using the latest Android Activity Result API.

### 3️: Apply Filters  
Each filter tap produces a *safe working bitmap* to prevent OpenGL crashes.

### 4️: GPU Rendering  
The PhotoFilter library applies effects using OpenGL shaders in real-time.

---

## Screenshots
<p align="center">
  <img src="screenshots/image1.jpeg" width="280">
  <img src="screenshots/image2.jpeg" width="280">
  <img src="screenshots/image3.jpeg" width="280">
</p>
