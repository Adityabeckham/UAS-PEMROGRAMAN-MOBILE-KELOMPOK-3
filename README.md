# AssessPro - Sistem Assessment Layanan Test 

AssessPro adalah aplikasi *mobile* berbasis **Android Native (Kotlin)** yang dirancang untuk memfasilitasi proses rekrutmen dan *assessment test* secara digital. Aplikasi ini memiliki dua portal utama: **Admin HR** (untuk mendaftarkan kandidat, memantau *progress* secara *real-time*, dan melihat hasil laporan tes) dan **Portal Peserta** (untuk mengerjakan soal tes *assessment*).

AssessPro dibangun menggunakan arsitektur MVVM (Model-View-ViewModel) dan menggunakan Firebase Firestore sebagai *backend database real-time* serta JavaMail API untuk pengiriman token via email.

---

## 🛠️ Tech Stack & Architecture

Aplikasi ini dibangun menggunakan standar industri modern untuk pengembangan Android Native:
- **Programming Language**: Kotlin
- **Architecture Pattern**: MVVM (Model-View-ViewModel)
- **UI & Styling**: XML Layouts & Material Design 3 (M3)
- **Asynchronous Programming**: Kotlin Coroutines & Flow
- **Backend & Database**: Firebase Firestore (NoSQL Real-time DB)
- **Authentication**: Firebase Authentication
- **Email Integration**: JavaMail API (SMTP SMTP protocol)
- **Build System**: Gradle (Kotlin DSL)

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

## Akun Demo
- HR EMAIL: `adminhr@gmail.com`
- HR PASSWORD: `adminhr123`
- Peserta Test: `Gunakan token yang di-generate dari dashboard HR`

### Panduan Testing untuk Dosen/Penilai
Untuk mencoba seluruh fitur aplikasi secara *End-to-End*, silakan ikuti langkah berikut:
1. Buka aplikasi, masuk ke **Portal Login HR Klien**.
2. Gunakan kredensial **Akun Demo HR** di atas untuk login.
3. Di *Dashboard* HR, masuk ke menu **Tambah Kandidat**. Masukkan nama, email (gunakan email asli untuk mengecek fitur SMTP otomatis), dan posisi jabatan.
4. Klik **Daftarkan & Generate Token**. Aplikasi akan menghasilkan 6-digit Token. Silakan catat/salin Token tersebut. *(Token juga otomatis terkirim ke email yang didaftarkan).*
5. Kembali ke menu utama (klik *Logout* jika perlu), lalu pilih **Masuk sebagai Peserta Ujian**.
6. Masukkan **Token** yang didapat tadi. Anda akan masuk ke halaman ujian.
7. Coba jawab soal-soal *Assessment* yang diberikan hingga selesai dan skor muncul.
8. Kembali lagi ke **Portal Login HR**, login, dan buka menu **Laporan**. Anda akan melihat hasil nilai dari tes yang baru saja dikerjakan!

## 📸 Screenshot Aplikasi

*HR Klien*

<p align="center">
  <img src="https://github.com/user-attachments/assets/e99ef2d1-9d3d-4b5d-ad6a-3caa28558aa6" width="200" alt="Portal Login"/>
  <img src="https://github.com/user-attachments/assets/0adf3cee-d866-4ba2-86b2-38f68ca22c8b" width="200" alt="HR Klien Login"/>
  <img src="https://github.com/user-attachments/assets/ebed1fe9-1f92-4566-a892-6529f369b0dc" width="200" alt="HR Tambah Kandidat"/>
  <img src="https://github.com/user-attachments/assets/15756ed5-78ec-4c1c-8f97-c3e9dc76fef9" width="200" alt="Generate Token"/>
  <img src="https://github.com/user-attachments/assets/02cd0447-c80b-47ee-9edc-b4eba5453950" width="200" alt="Bagikan Token"/>
  <img src="https://github.com/user-attachments/assets/1ef51a57-9c0b-4eb1-889e-59d7241fa863" width="200" alt="Laporan Test"/>
</p>

*Peserta Test / Kandidat*

<p align="center">
  <img src="https://github.com/user-attachments/assets/e99ef2d1-9d3d-4b5d-ad6a-3caa28558aa6" width="200" alt="Portal Login"/>
  <img src="https://github.com/user-attachments/assets/61bbd65d-1577-4cb9-8b3d-03ece1acce2c" width="200" alt="Kandidat menerima Email Token Login"/>
  <img src="https://github.com/user-attachments/assets/956f2c4e-08c7-4a08-a00d-727dc805147c" width="200" alt="Kandidat Login Token"/>
  <img src="https://github.com/user-attachments/assets/809b379f-802e-4bef-96ea-f87b65a2afcc" width="200" alt="Kandidat Mengerjakan Test"/>
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
