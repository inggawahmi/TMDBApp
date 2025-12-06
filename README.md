# TMDBApp

TMDBApp adalah aplikasi Android sederhana yang menggunakan API dari The Movie Database (TMDB) untuk menampilkan daftar film.  

## 🎯 What is this  

- Aplikasi ini mengambil data film dari TMDB API dan menampilkan list film kepada pengguna.  
- Ditulis dengan Kotlin.  

## 💡 Features / What it does  

- Mengambil daftar film dari TMDB (list film)  
- Menampilkan film dalam bentuk list / grid (sesuai implementasi UI)  
- Struktur dasar aplikasi sudah disiapkan (proyek, konfigurasi Gradle, folder `app`, dsb)  

## 🛠️ How to run / Setup  

1. Clone repo ini:  
   ```bash
   git clone https://github.com/inggawahmi/TMDBApp.git
   ```
2. Buka di Android Studio.

3. Tambahkan TMDB API key (kalau dibutuhkan — tergantung implementasi; misalnya di local.properties atau gradle.properties).

4. Build & jalankan aplikasi di emulator atau perangkat Android.

## 📂 Project Structure
```
/
├── app/            — Modul aplikasi  
├── .idea/          — Konfigurasi IDE Android Studio  
├── build.gradle.kts  
├── gradle/         — Wrapper gradle  
├── gradlew, gradlew.bat, settings.gradle.kts, dsb  
├── gradle.properties  
└── .gitignore
```
