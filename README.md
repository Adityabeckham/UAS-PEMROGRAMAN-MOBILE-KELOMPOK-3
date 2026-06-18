# AssessPro - Sistem Assessment HR Terpadu

AssessPro adalah aplikasi *mobile* berbasis **Android Native (Kotlin)** yang dirancang untuk memfasilitasi proses rekrutmen dan *assessment test* secara digital. Aplikasi ini memiliki dua portal utama: **Admin HR** (untuk mendaftarkan kandidat, memantau *progress* secara *real-time*, dan melihat hasil laporan tes) dan **Portal Peserta** (untuk mengerjakan soal tes *assessment*).

AssessPro dibangun menggunakan arsitektur MVVM (Model-View-ViewModel) dan menggunakan Firebase Firestore sebagai *backend database real-time* serta JavaMail API untuk pengiriman token via email.

---

## 👥 Kelompok 3

| Nama | NPM | Peran |
| --- | --- | --- |
| Aditya Beckham | [ISI NPM ANDA] | [PERAN ANDA] |
| Max | [ISI NPM MAX] | [PERAN MAX] |
| [NAMA ANGGOTA 3] | [NPM 3] | [PERAN 3] |

---

## 📹 Video Penjelasan Project

Tonton video presentasi dan demo lengkap aplikasi kami di link berikut:
👉 **[LINK VIDEO YOUTUBE / GOOGLE DRIVE ANDA DI SINI]**

---

## 📸 Screenshot Aplikasi

*(Ganti URL gambar di bawah ini dengan screenshot aplikasi Anda yang sebenarnya)*

<p align="center">
  <img src="[LINK_SCREENSHOT_1]" width="250" alt="Screenshot 1"/>
  <img src="[LINK_SCREENSHOT_2]" width="250" alt="Screenshot 2"/>
  <img src="[LINK_SCREENSHOT_3]" width="250" alt="Screenshot 3"/>
</p>

---

## 🚀 Cara Menjalankan/Cloning Proyek

Berikut adalah langkah-langkah untuk menjalankan *source code* ini secara lokal di komputer Anda:

1. **Clone Repository**
   ```bash
   git clone https://github.com/Adityabeckham/UAS-PEMROGRAMAN-MOBILE-KELOMPOK-3.git
   ```
2. **Buka di Android Studio**
   - Buka Android Studio.
   - Pilih `Open an existing Android Studio project`.
   - Cari dan pilih folder hasil *clone* tadi.
3. **Setup Firebase & Credentials (Wajib)**
   - Pastikan file `google-services.json` sudah ada di dalam folder `app/`.
   - Buka file `app/src/main/java/com/example/uas_pemrogramanmobile_kelompok3/utils/EmailSenderUtil.kt`.
   - Ganti `SENDER_APP_PASSWORD` dengan *App Password* Gmail milik Anda sendiri (karena pengiriman Token menggunakan SMTP Google).
4. **Build Project & Jalankan**
   - Tunggu hingga sinkronisasi Gradle selesai (`Gradle Sync`).
   - Tekan ikon hijau **Run / Play** di atas layar atau tekan `Shift + F10` untuk menjalankan aplikasi di Emulator Android atau perangkat fisik.

---

## 📂 Struktur Khusus Pengumpulan UAS

* **APK Hasil Build:** Tersedia di dalam folder `/apk` di *root directory* repositori ini.
* **Laporan OOAD:** Tersedia di dalam folder `/docs` atau `/ooad`.

*(Sistem ini dibangun secara utuh menggunakan Android Native Activity/Fragment dan RecyclerView, tanpa bantuan platform cross-platform seperti Flutter atau React Native sesuai dengan instruksi UAS).*
