# Panduan Kontribusi (CONTRIBUTING.md)

Selamat datang di repositori proyek **Sistem Layanan Assessment Klien** untuk Tugas Besar UAS Pemrograman Mobile 1.

Dokumen ini memuat aturan main wajib bagi seluruh anggota tim (Aditya, Anisa, dan Max) agar proses pengembangan berjalan rapi, bebas *error*, dan terstruktur sesuai standar industri perangkat lunak (*Agile Scrum*).

---

## 👥 Peran dan Tanggung Jawab Tim
* **Aditya (Tech Lead / Backend):** Arsitektur proyek, integrasi Firebase Firestore/Auth, struktur database, resolusi konflik Git, dan *Code Review* akhir.
* **Anisa (UI/UX / Frontend):** Desain antarmuka (XML), navigasi aplikasi, *Data Binding* ke UI, dan pengalaman pengguna (*User Experience*).
* **Max (QA / Feature Developer):** Logika fitur spesifik (Timer, Validasi Input), pengujian fitur (*End-to-End Testing*), dan keamanan *client-side*.

---

## 🛑 Aturan Emas (Golden Rules)
1. **DILARANG KERAS** melakukan `git push` secara langsung ke branch `main`.
2. Branch `main` adalah versi *production-ready*. Kodenya harus selalu bisa di-*build* dan dijalankan tanpa *error*.
3. Setiap tugas **wajib** dikerjakan di branch terpisah (Branch Fitur) dan digabungkan melalui **Pull Request (PR)**.

---

## 🔄 WORKFLOW WAJIB: Langkah Demi Langkah Koding Harian

Agar tidak terjadi *Merge Conflict* (kode yang bertabrakan dan rusak), **setiap kali Anda akan mulai mengerjakan task baru**, ikuti urutan langkah 1 sampai 7 ini dengan teliti:

### Langkah 1: Ambil Tugas di Kanban Board
* Buka tab **Projects** di GitHub.
* Pilih tugas di kolom **To Do**.
* Klik tugas tersebut, assign ke nama Anda ("Assign to me").
* Pindahkan kartu tugas tersebut ke kolom **In Progress**.

### Langkah 2: WAJIB Update Kode Lokal (Sync)
**JANGAN langsung membuat branch baru.** Anda harus memastikan laptop Anda memiliki kode terbaru dari pekerjaan teman-teman yang lain terlebih dahulu. Buka terminal di Android Studio dan ketik:
```bash
# 1. Pastikan Anda berada di branch main
git checkout main

# 2. Tarik semua update kode terbaru dari GitHub ke laptop Anda
git pull origin main

- (Jika muncul tulisan "Already up to date", berarti kode Anda sudah paling baru).

```
### Langkah 3: Buat Branch Fitur Baru
Setelah main Anda up-to-date, barulah buat branch baru khusus untuk tugas Anda. Gunakan format feature/nama-tugas atau fix/nama-bug.
```bash
git checkout -b feature/tugas-anda
# Contoh: git checkout -b feature/ui-login
```
### Langkah 4: Koding dan Commit Berkala
Silakan kerjakan tugas Anda di Android Studio. Lakukan commit secara berkala jika ada progres (jangan menunggu sampai semua selesai baru di-commit). Gunakan pesan yang jelas:
```bash
git add .
git commit -m "feat: membuat layout XML untuk halaman login"
# atau
git commit -m "fix: memperbaiki tombol login yang tidak bisa diklik"
```
### Langkah 5: Push Branch ke GitHub
Jika tugas sudah selesai sepenuhnya sesuai Requirement di TASK_MANAGEMENT.md, kirim branch Anda ke GitHub.
```bash
git push origin feature/tugas-anda
```
### Langkah 6: Buka Pull Request (PR) & Link Kanban
```bash
- Buka halaman repositori di GitHub.

- Klik tombol hijau "Compare & pull request".

- Beri judul yang jelas.

- SANGAT PENTING: Di kotak deskripsi, ketik Closes #ID_ISSUE (Contoh: Closes #5) agar tugas Anda di Kanban otomatis selesai saat PR digabungkan.

- Pindahkan kartu tugas Anda di Kanban ke kolom In Review.
```
### Langkah 7: Minta Code Review
``` bash
- Beritahu tim di grup chat: "Halo, tugasku udah selesai. Tolong bantu review PR-nya ya."

- PR Anda tidak akan bisa digabungkan (merge) sampai minimal 1 anggota tim lain mengecek dan menekan tombol Approve.

- Setelah di-approve dan di-merge ke main, branch fitur Anda bisa dihapus dari GitHub. Kartu Kanban akan otomatis pindah ke Done.
```
### 🚑 Panduan Darurat: Mengatasi Merge Conflict
```bash
- Jika saat membuat PR GitHub mengatakan "Can't automatically merge", jangan panik. Lakukan ini di laptop Anda:

1. Pastikan Anda berada di branch fitur Anda.

2. Tarik kode terbaru dari main: git pull origin main

3. Android Studio akan menunjukkan file mana yang bentrok (biasanya dengan tanda merah).

4. Buka file tersebut, pilih kode mana yang mau dipertahankan.

5. `Jika sudah rapi, lakukan commit lagi dan push. Konflik di PR GitHub akan otomatis hilang.
```


