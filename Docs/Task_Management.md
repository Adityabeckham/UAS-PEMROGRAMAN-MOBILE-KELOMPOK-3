# Panduan Manajemen Tugas (Task Management)

Dokumen ini memuat rincian seluruh tugas (tasks), pembagian peran, dan persyaratan teknis (requirements) untuk pengembangan **Sistem Layanan Assessment Klien**. Setiap tugas di bawah ini harus dipetakan ke dalam **GitHub Projects (Kanban Board)**.

## 📌 Alur Kerja Kanban (Kanban Workflow)
Setiap anggota tim (Aditya, Anisa, Max) wajib mematuhi alur pergerakan kartu di GitHub Projects:
1. **To Do:** Task yang siap dikerjakan pada Sprint berjalan.
2. **In Progress:** Task yang sedang dikoding. (Setiap orang maksimal memegang 1 task di sini).
3. **In Review:** Kodingan selesai, *Pull Request* (PR) sudah dibuat. Menunggu *code review* dari Aditya.
4. **Done:** PR sudah di-*merge* ke branch `main`.

---

## 🏃‍♂️ Rencana Sprint & Pembagian Tugas (Sprint Backlog)

### SPRINT 1: Fondasi Sistem & Autentikasi (Minggu 1)
Fokus pada pembuatan struktur proyek, UI dasar, dan sistem *login* yang aman.

#### Task 1.1: Setup Repositori & Firebase (Aditya)
* **Assignee:** Aditya
* **Deskripsi:** Menginisialisasi proyek Android, menghubungkan ke Firebase, dan menyusun arsitektur proyek.
* **Requirements / Security:**
    * Menerapkan *Empty Views Activity* dan struktur MVC/MVVM.
    * Menghubungkan `google-services.json` dengan aman (pastikan tidak *commit* kunci sensitif jika project bersifat publik, atau gunakan environment variables).
    * Mengaktifkan *Firebase Authentication* (Email/Password) dan *Firestore Database*.

#### Task 1.2: UI Halaman Login & Dashboard Klien (Anisa)
* **Assignee:** Anisa
* **Deskripsi:** Membuat antarmuka pengguna untuk halaman login dan kerangka halaman utama klien.
* **Requirements / Security:**
    * Menggunakan `ConstraintLayout` agar responsif di berbagai ukuran layar.
    * **Validasi UI:** *Button Login* hanya bisa diklik jika form email dan password tidak kosong.
    * Input password harus menggunakan tipe `textPassword` (disembunyikan dengan titik/bintang).
    * Tidak menggunakan *hardcoded strings* (semua teks wajib dimasukkan ke `res/values/strings.xml`).

#### Task 1.3: Logika Autentikasi & Validasi Keamanan (Max)
* **Assignee:** Max
* **Deskripsi:** Menyambungkan UI Login buatan Anisa dengan Firebase Auth buatan Aditya, beserta validasi *input*-nya.
* **Requirements / Security:**
    * **Validasi Email:** Pengecekan format email menggunakan Regex atau `Patterns.EMAIL_ADDRESS.matcher`.
    * **Validasi Password:** Minimal 6 karakter.
    * Menampilkan *Toast* atau *Snackbar* error yang jelas jika kredensial salah (misal: "Email atau Password tidak sesuai").
    * Mengarahkan *user* ke `DashboardActivity` jika login sukses, dan menutup halaman login (`finish()`) agar *user* tidak bisa kembali ke halaman login dengan menekan tombol *Back*.

---

### SPRINT 2: Manajemen Peserta & Distribusi Token (Minggu 2)
Fokus pada alur Klien menginput data peserta dan memberikan akses ujian.

#### Task 2.1: UI Form Input Peserta (Anisa)
* **Assignee:** Anisa
* **Deskripsi:** Membuat antarmuka (pop-up dialog atau halaman baru) bagi Klien untuk mendaftarkan kandidat.
* **Requirements:**
    * Form berisi: Nama Lengkap, Email Kandidat, dan Posisi yang dilamar.
    * Desain yang bersih dengan tombol "Simpan" dan "Batal".

#### Task 2.2: Logika Generate Token & CRUD Firestore (Aditya)
* **Assignee:** Aditya
* **Deskripsi:** Menangani penyimpanan data peserta baru ke Firestore dan mengamankan *database rules*.
* **Requirements / Security:**
    * Membuat fungsi *auto-generate* token unik (kombinasi 6 huruf/angka) untuk peserta.
    * Menyimpan data ke collection `participants` di Firestore.
    * **Firestore Security Rules:** Memastikan hanya *user* dengan role 'Klien' yang bisa melakukan aksi *Write* ke collection `participants`.

#### Task 2.3: Integrasi Pengiriman Token (Max)
* **Assignee:** Max
* **Deskripsi:** Menampilkan token yang di-generate sistem agar bisa disalin/dikirimkan oleh Klien.
* **Requirements:**
    * Menampilkan detail token yang berhasil dibuat di Dashboard Klien.
    * (Opsional/Bonus) Menggunakan *Intent Action Send* agar Klien bisa membagikan token langsung via aplikasi *messaging* atau *email client* bawaan HP.

---

### SPRINT 3: Modul Ujian & Sinkronisasi Real-Time (Minggu 3)
Fokus pada pengalaman pengerjaan soal oleh Peserta dan pemantauan oleh Klien.

#### Task 3.1: UI Sesi Pengerjaan Soal (Anisa)
* **Assignee:** Anisa
* **Deskripsi:** Membuat tampilan saat peserta mengerjakan ujian.
* **Requirements:**
    * Tampilan teks soal yang jelas.
    * Gunakan `RadioGroup` dan `RadioButton` untuk pilihan ganda (A, B, C, D).
    * Terdapat indikator nomor soal (misal: "Soal 1 dari 20").
    * Tombol "Berikutnya", "Sebelumnya", dan "Selesai".

#### Task 3.2: Logika Anti-Cheat & Timer Ujian (Max)
* **Assignee:** Max
* **Deskripsi:** Menerapkan logika dasar untuk menjaga integritas ujian.
* **Requirements / Security:**
    * **Timer:** Menerapkan *CountDownTimer* yang terlihat di layar.
    * **Anti-Cheat (Basic):** Melakukan *override* fungsi `onPause()`. Jika peserta meminimalkan aplikasi (pindah ke tab/aplikasi lain) saat tes berlangsung, sistem menampilkan peringatan *"Dilarang keluar aplikasi selama tes berlangsung"*.

#### Task 3.3: Firestore Real-Time Sync (Aditya)
* **Assignee:** Aditya
* **Deskripsi:** Mengintegrasikan penyimpanan jawaban sementara dan *dashboard real-time*.
* **Requirements / Security:**
    * Setiap kali peserta menekan "Berikutnya", jawaban tersimpan ke Firestore secara otomatis (*auto-save*).
    * Klien Dashboard menggunakan `addSnapshotListener` untuk melihat peserta mana yang statusnya 'sedang mengerjakan' dan 'sudah selesai' secara *real-time*.

---

### SPRINT 4: Laporan & Quality Assurance (QA) (Minggu 4)
Fokus pada perhitungan nilai akhir, *testing*, dan finalisasi tugas akhir.

#### Task 4.1: UI Halaman Laporan Klien (Anisa)
* **Assignee:** Anisa
* **Deskripsi:** Membuat antarmuka untuk menampilkan hasil akhir ujian kandidat.
* **Requirements:**
    * Desain *List/RecyclerView* yang menampilkan nama kandidat dan Skor Akhir.

#### Task 4.2: Kalkulasi Skor & Finalisasi Database (Aditya)
* **Assignee:** Aditya
* **Deskripsi:** Melakukan pencocokan jawaban peserta dengan kunci jawaban.
* **Requirements / Security:**
    * Logika pencocokan jawaban tidak boleh ada di *client-side* (peserta) agar tidak bisa di-hack. Kalkulasi skor dilakukan saat status berubah menjadi 'selesai'.
    * Skor akhir disimpan secara permanen di collection `reports`.

#### Task 4.3: End-to-End Testing & Bug Fixing (Max)
* **Assignee:** Max
* **Deskripsi:** Memastikan seluruh fitur dari awal sampai akhir berjalan mulus.
* **Requirements:**
    * Mengetes *Login* dengan akun salah dan benar.
    * Mengetes *input* peserta dengan field kosong (memastikan validasi bekerja).
    * Mencoba koneksi internet terputus di tengah tes (memeriksa performa *offline-persistence* Firebase).