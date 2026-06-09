# Skema Database (DATABASE_SCHEMA.md)

Dokumen ini menjelaskan struktur data dan skema koleksi database **Firebase Firestore** yang digunakan dalam proyek **Sistem Layanan Assessment Klien**. Karena Firestore adalah database NoSQL, data diorganisasikan ke dalam *Collections*, *Documents*, dan *Fields/Maps*.

---

## 📂 Ringkasan Koleksi (Collections Overview)

Sistem ini menggunakan 6 koleksi utama untuk mengelola bisnis layanan psikotes rekrutmen:
1. `users` : Menyimpan data autentikasi dan peran (roles) pengguna.
2. `clients` : Menyimpan data perusahaan yang membeli jasa assessment.
3. `assessments` : Menyimpan paket soal psikotes/ujian.
4. `participants` : Menyimpan data kandidat/peserta tes yang didaftarkan.
5. `sessions` : Mengelola pengerjaan tes secara *real-time* dan log kecurangan.
6. `reports` : Menyimpan hasil akhir kalkulasi nilai dan interpretasi psikologi.

---

## 📑 Detail Struktur Koleksi

### 1. Koleksi: `users`
Koleksi ini digunakan untuk menentukan hak akses setelah pengguna berhasil login menggunakan Firebase Authentication.
* **Path:** `/users/{user_id}`
* **Document ID:** Disamakan dengan `UID` dari Firebase Auth.

| Field Name | Data Type | Description |
| :--- | :--- | :--- |
| `user_id` | String | ID unik dari Firebase Auth. |
| `email` | String | Alamat email terdaftar. |
| `role` | String | Peran pengguna (`client` atau `participant`). |
| `created_at` | Timestamp | Waktu pembuatan akun. |

---

### 2. Koleksi: `clients`
Menyimpan profil perusahaan klien yang menyewa jasa assessment.
* **Path:** `/clients/{client_id}`

| Field Name | Data Type | Description |
| :--- | :--- | :--- |
| `client_name` | String | Nama perusahaan klien. |
| `pic_name` | String | Nama penanggung jawab/HRD dari perusahaan tersebut. |
| `email` | String | Email resmi perusahaan klien. |
| `created_at` | Timestamp | Tanggal bergabung dalam layanan. |

---

### 3. Koleksi: `assessments`
Menyimpan bank soal psikotes yang dibuat untuk perusahaan klien tertentu.
* **Path:** `/assessments/{assessment_id}`

| Field Name | Data Type | Description |
| :--- | :--- | :--- |
| `client_id` | String (Ref) | Referensi ke ID koleksi `clients`. |
| `title` | String | Nama ujian (contoh: "Tes Logika & Penalaran"). |
| `duration_minutes`| Number | Durasi waktu pengerjaan dalam satuan menit. |
| `questions` | Array (Objects)| Daftar pertanyaan ujian. |
| ↳ `question_id` | String | ID unik per soal. |
| ↳ `question_text`| String | Teks pertanyaan soal. |
| ↳ `options` | Array (Strings)| Pilihan jawaban (A, B, C, D). |
| ↳ `correct_answer`| String | Kunci jawaban benar (untuk kalkulasi otomatis). |

---

### 4. Koleksi: `participants`
Menyimpan daftar kandidat peserta yang diinput oleh Klien Perusahaan melalui dashboard.
* **Path:** `/participants/{participant_id}`

| Field Name | Data Type | Description |
| :--- | :--- | :--- |
| `client_id` | String (Ref) | Referensi ke perusahaan yang mendaftarkan kandidat. |
| `name` | String | Nama lengkap peserta/kandidat. |
| `email` | String | Email kandidat tempat pengiriman token akses. |
| `token` | String | Kode unik (6 digit) untuk login peserta ke sesi tes. |
| `status` | String | Status pengerjaan (`pending`, `active`, `finished`). |

---

### 5. Koleksi: `sessions` (Real-Time Tracking)
Koleksi krusial yang dipantau secara langsung (*real-time sync*) oleh Klien Perusahaan saat ujian berlangsung.
* **Path:** `/sessions/{session_id}`

| Field Name | Data Type | Description |
| :--- | :--- | :--- |
| `participant_id` | String (Ref) | Referensi ke ID koleksi `participants`. |
| `assessment_id` | String (Ref) | Referensi ke jenis tes yang sedang dikerjakan. |
| `client_id` | String (Ref) | Diperlukan untuk query filter monitoring sisi klien. |
| `start_time` | Timestamp | Waktu mulai menekan tombol mulai tes. |
| `current_progress`| Number | Indeks nomor soal terakhir yang diakses peserta. |
| `answers` | Map (Key-Value)| Menyimpan jawaban sementara. Contoh: `{"q1": "A", "q2": "C"}`. |
| `status` | String | Status sesi (`in_progress`, `submitted`, `timeout`). |
| `last_updated` | Timestamp | Pembaruan terakhir (indikator detak aktivitas peserta). |
| `cheating_logs` | Array (Objects)| Log pelacakan kejujuran (anti-cheat). |
| ↳ `event` | String | Jenis pelanggaran (contoh: "app_minimized"). |
| ↳ `timestamp` | Timestamp | Waktu saat kecurangan terdeteksi. |

---

### 6. Koleksi: `reports`
Menyimpan hasil rekapitulasi penilaian akhir yang siap diunduh oleh Klien Perusahaan.
* **Path:** `/reports/{report_id}`

| Field Name | Data Type | Description |
| :--- | :--- | :--- |
| `participant_id` | String (Ref) | Referensi ke data peserta pemilik nilai. |
| `assessment_id` | String (Ref) | Jenis tes yang telah diselesaikan. |
| `client_id` | String (Ref) | Hak kepemilikan laporan untuk perusahaan tertentu. |
| `final_score` | Number | Skor akhir hasil pencocokan jawaban. |
| `interpretation` | String | Hasil analisis psikologis otomatis/manual. |
| `generated_at` | Timestamp | Waktu penulisan laporan selesai dibuat. |

---

## 🔒 Aturan Keamanan Firebase (Basic Security Rules)

Sesuai standar keamanan, berikut aturan dasar yang wajib diterapkan pada Firebase Console agar peserta tidak dapat memanipulasi data nilai:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    
    // Aturan Koleksi Users
    match /users/{userId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
    
    // Peserta hanya bisa meng-update jawaban mereka sendiri di koleksi sessions
    match /sessions/{sessionId} {
      allow read, write: if request.auth != null && resource.data.participant_id == request.auth.uid;
      // Mengizinkan Client membaca semua session berdasarkan client_id mereka
      allow read: if request.auth != null && get(/databases/$(database)/documents/users/$(request.auth.uid)).data.role == 'client';
    }
    
    // Hanya Client yang bisa membaca Report akhir
    match /reports/{reportId} {
      allow read: if request.auth != null && get(/databases/$(database)/documents/users/$(request.auth.uid)).data.role == 'client';
      allow write: if false; // Report hanya bisa ditulis melalui sistem/backend internal
    }
  }
}