# Product Requirement Document (PRD): Sistem Layanan Assessment Klien

## 1. Overview
### 1.1 Deskripsi Produk
Aplikasi mobile berbasis Android yang memfasilitasi proses assessment klien. Sistem memungkinkan perusahaan untuk mengelola data peserta secara efisien dan memfasilitasi peserta dalam melakukan tes secara real-time.

### 1.2 Tujuan (Goals)
* Mengotomatisasi distribusi akses tes melalui sistem token.
* Menyediakan pemantauan pengerjaan tes secara real-time bagi pihak perusahaan.
* Memastikan integritas data jawaban peserta melalui sinkronisasi cloud yang stabil.

---

## 2. Pengguna (User Personas)
* **Klien Perusahaan (Admin):** Pihak yang memiliki wewenang untuk mendaftarkan peserta dan mengakses hasil akhir tes.
* **Peserta Test:** Pengguna yang menjalankan sesi tes berdasarkan data dan akses yang diberikan oleh Klien Perusahaan.

---

## 3. Fitur Utama & User Stories

| ID | Fitur | User Story | Prioritas |
| :--- | :--- | :--- | :--- |
| F01 | Autentikasi | Sebagai pengguna, saya ingin login agar dapat mengakses dashboard sesuai peran saya. | P0 |
| F02 | Manajemen Peserta | Sebagai Klien, saya ingin menginput data peserta agar mereka terdaftar di sistem. | P0 |
| F03 | Distribusi Token | Sebagai Klien, saya ingin sistem mengirimkan token otomatis ke email peserta. | P1 |
| F04 | Sesi Tes | Sebagai Peserta, saya ingin login ke portal test assesment emnggunakan token dari klien. | P0 |
| F05 | Sesi Tes | Sebagai Peserta, saya ingin menjawab soal agar proses assessment berjalan. | P0 |
| F06 | Monitoring Real-time | Sebagai Klien, saya ingin melihat progres tes peserta secara langsung. | P1 |
| F07 | Laporan Hasil | Sebagai Klien, saya ingin mengunduh laporan hasil tes peserta. | P2 |

---

## 4. Kebutuhan Teknis (Technical Requirements)
* **Frontend:** Android (Kotlin/Java) menggunakan template Empty Views Activity.
* **Database & Backend:** Firebase Firestore (Dukungan real-time sync dan offline persistence).
* **Arsitektur:** Mengacu pada prinsip MVVM (Model-View-ViewModel) untuk menjaga kode tetap rapi.
* **Git Workflow:** Kolaborasi berbasis fitur (feature branching) di GitHub.

---

## 5. Struktur Data (Firestore)
* **Collection `users`:** Menyimpan email, password (hash), dan role.
* **Collection `participants`:** Menyimpan detail peserta dan token akses.
* **Collection `sessions`:** Menyimpan status tes, jawaban (map), dan timestamp terakhir untuk kebutuhan real-time monitoring.

---

## 6. Alur Sistem (Logic Workflow)
1. **Registrasi Peserta:** Klien memasukkan data peserta -> Sistem generate token -> Email dikirim ke peserta.
2. **Sesi Tes:** Peserta login dengan token -> Sistem membuka sesi -> Peserta menjawab soal -> Jawaban dikirim secara real-time ke Firestore.
3. **Monitoring:** Klien membuka dashboard -> Snapshot listener di Firestore mendeteksi perubahan data dari peserta -> Dashboard terupdate otomatis.

---

## 7. Rencana Pengembangan (Roadmap)
* **Sprint 1:** Setup repositori, konfigurasi Firebase, dan pembuatan UI dasar (Login & Dashboard).
* **Sprint 2:** Implementasi modul input peserta dan sistem email token.
* **Sprint 3:** Implementasi modul Assessment (pengerjaan soal) dan sinkronisasi real-time.
* **Sprint 4:** Laporan `README.md` project dan Quality Assurance (Testing).
