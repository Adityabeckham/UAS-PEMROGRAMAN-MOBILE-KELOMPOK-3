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
| F01 | Autentikasi HR | Sebagai Klien (HR), saya ingin login dengan email dan password agar dapat mengakses dashboard manajemen. | P0 |
| F02 | Manajemen Peserta | Sebagai Klien, saya ingin menginput data peserta baru agar mereka terdaftar di dalam sistem assessment. | P0 |
| F03 | Distribusi Token | Sebagai Klien, saya ingin sistem memunculkan dan mengirimkan token unik otomatis untuk akses tes peserta. | P1 |
| F04 | Login Peserta | Sebagai Peserta, saya ingin login ke portal assessment menggunakan token yang di-generate oleh Klien. | P0 |
| F05 | Sesi Tes | Sebagai Peserta, saya ingin menjawab soal dengan antarmuka yang fokus agar proses assessment berjalan lancar. | P0 |
| F06 | Monitoring Real-time | Sebagai Klien, saya ingin melihat progres pengerjaan tes peserta secara langsung (live) dari dashboard. | P1 |
| F07 | Laporan Hasil | Sebagai Klien, saya ingin mengunduh laporan skor dan hasil tes peserta yang sudah selesai. | P2 |

---

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
## 6. Alur Sistem End-to-End (Logic Workflow)

Alur aplikasi ini dibagi menjadi dua siklus yang saling terhubung antara Klien (HR) dan Peserta:

**Fase 1: Persiapan Klien (HRD)**
1. Klien membuka aplikasi dan melakukan login (F01).
2. Klien masuk ke Dashboard dan mendaftarkan kandidat peserta baru (F02).
3. Sistem menghasilkan Token 6-digit dan Klien mendistribusikannya ke Peserta (F03).

**Fase 2: Eksekusi Ujian (Peserta) & Monitoring (Klien)**
4. Peserta membuka aplikasi, memilih menu "Login Peserta", dan memasukkan Token (F04).
5. Peserta mulai mengerjakan soal satu per satu di dalam Sesi Tes (F05).
6. Di saat yang sama, Klien dapat memantau indikator progres peserta yang sedang bergerak secara *real-time* di Dashboard (F06).

**Fase 3: Penyelesaian (Klien)**
7. Setelah Peserta menekan tombol "Selesai", sistem mengkalkulasi nilai.
8. Klien membuka menu Laporan untuk melihat skor akhir dan mengunduh detail hasilnya (F07).

---

---

## 7. Rencana Pengembangan (Roadmap)
* **Sprint 1:** Setup repositori, konfigurasi Firebase, dan pembuatan UI dasar (Login & Dashboard).
* **Sprint 2:** Implementasi modul input peserta dan sistem email token.
* **Sprint 3:** Implementasi modul Assessment (pengerjaan soal) dan sinkronisasi real-time.
* **Sprint 4:** Laporan `README.md` project dan Quality Assurance (Testing).
  
---
