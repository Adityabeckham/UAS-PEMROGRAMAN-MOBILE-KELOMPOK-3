# AssessPro - Sistem Assessment Layanan Test 

AssessPro adalah aplikasi *mobile* berbasis **Android Native (Kotlin)** yang dirancang untuk memfasilitasi proses rekrutmen dan *assessment test* secara digital. Aplikasi ini memiliki dua portal utama: **Admin HR** (untuk mendaftarkan kandidat, memantau *progress* secara *real-time*, dan melihat hasil laporan tes) dan **Portal Peserta** (untuk mengerjakan soal tes *assessment*).

AssessPro dibangun menggunakan arsitektur MVVM (Model-View-ViewModel) dan menggunakan Firebase Firestore sebagai *backend database real-time* serta JavaMail API untuk pengiriman token via email.

---

## 👥 Kelompok 3

| Nama | NPM | Peran |
| --- | --- | --- |
| Aditya Beckham | 24552011066 | Software Engineer Lead |
| Anisa Ansuya Henna | 24552011020 | Frontend Developer |
| Max Devon | 24552011094 | Backend Developer |

---

## 📹 Video Penjelasan Project

Tonton video presentasi dan demo lengkap aplikasi kami di link berikut:
👉 **[LINK VIDEO YOUTUBE / GOOGLE DRIVE ANDA DI SINI]**

---

## 📸 Screenshot Aplikasi

*HR Klien Login*

<p align="center">
  <img src="https://github.com/user-attachments/assets/e99ef2d1-9d3d-4b5d-ad6a-3caa28558aa6" width="200" alt="Portal Login"/>
  <img src="https://github.com/user-attachments/assets/0adf3cee-d866-4ba2-86b2-38f68ca22c8b" width="200" alt="HR Klien Login"/>
  <img src="https://github.com/user-attachments/assets/ebed1fe9-1f92-4566-a892-6529f369b0dc" width="200" alt="HR Tambah Kandidat"/>
  <img src="https://github.com/user-attachments/assets/15756ed5-78ec-4c1c-8f97-c3e9dc76fef9" width="200" alt="Generate Token"/>
  <img src="https://github.com/user-attachments/assets/02cd0447-c80b-47ee-9edc-b4eba5453950" width="200" alt="Bagikan Token"/>
  <img src="https://github.com/user-attachments/assets/1ef51a57-9c0b-4eb1-889e-59d7241fa863" width="200" alt="Laporan Test"/>
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
