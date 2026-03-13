# Tugas CRUD KTP - Spring Boot & MySQL

Aplikasi CRUD (Create, Read, Update, Delete) untuk manajemen data Kartu Tanda Penduduk (KTP) menggunakan **Spring Boot** sebagai backend REST API dan **HTML/CSS/JavaScript (jQuery Ajax)** sebagai frontend client-side. Semua operasi CRUD berjalan tanpa refresh halaman menggunakan Ajax.

## 🛠 Teknologi yang Digunakan

| Layer      | Teknologi                              |
|------------|----------------------------------------|
| Backend    | Spring Boot 4.0.3, Java 25            |
| Database   | MySQL                                  |
| ORM        | Spring Data JPA (Hibernate)            |
| Frontend   | HTML5, CSS3, JavaScript, jQuery Ajax   |
| Build Tool | Maven                                  |

### Dependencies

| Dependency           | Fungsi                                          |
|----------------------|-------------------------------------------------|
| Spring Web           | Framework REST API (Controller, JSON)            |
| Spring Data JPA      | ORM untuk interaksi dengan database MySQL        |
| MySQL Driver         | JDBC Driver koneksi ke MySQL                     |
| Lombok               | Mengurangi boilerplate code (getter/setter/dll)  |
| Validation           | Validasi data menggunakan anotasi                |
| MapStruct            | Mapping otomatis antara Entity dan DTO           |

---

## 📁 Struktur Project

```
praktikum2crudktp/
├── src/main/
│   ├── java/com/deploy/praktikum2crudktp/
│   │   ├── controller/
│   │   │   └── KtpController.java           # REST Controller untuk handle request API
│   │   ├── mapper/
│   │   │   └── KtpMapper.java               # Interface MapStruct (Entity <-> DTO)
│   │   ├── model/
│   │   │   ├── dto/
│   │   │   │   ├── KtpAddRequest.java       # DTO untuk request tambah/update data
│   │   │   │   └── KtpDto.java              # DTO untuk response data KTP
│   │   │   └── entity/
│   │   │       └── Ktp.java                 # JPA Entity (mapping ke tabel database)
│   │   ├── repository/
│   │   │   └── KtpRepository.java           # Spring Data JPA Repository
│   │   ├── service/
│   │   │   ├── KtpService.java              # Service Interface
│   │   │   └── Impl/
│   │   │       └── KtpServiceImpl.java      # Service Implementation (logika bisnis)
│   │   ├── util/
│   │   │   └── ValidationUtil.java          # Utility class untuk validasi data
│   │   └── Praktikum2CrudktpApplication.java  # Main Application
│   └── resources/
│       ├── application.properties           # Konfigurasi database & aplikasi
│       └── static/
│           ├── index.html                   # Halaman utama (frontend)
│           ├── css/
│           │   └── style.css                # Stylesheet
│           └── js/
│               └── app.js                   # jQuery Ajax untuk CRUD
├── .env                                     # Environment variables (database config)
└── pom.xml                                  # Maven dependencies
```

### Package yang Diimplementasikan

| Package      | File                | Tipe      | Keterangan                                         |
|--------------|---------------------|-----------|-----------------------------------------------------|
| controller   | KtpController       | Class     | Handle 5 endpoint CRUD                              |
| mapper       | KtpMapper           | Interface | Konversi otomatis Entity ke DTO menggunakan MapStruct|
| model/dto    | KtpAddRequest       | Class     | DTO untuk request tambah & update data               |
| model/dto    | KtpDto              | Class     | DTO untuk response data KTP                          |
| model/entity | Ktp                 | Class     | Entity JPA mapping ke tabel ktp                      |
| repository   | KtpRepository       | Interface | Akses database dengan Spring Data JPA                |
| service      | KtpService          | Interface | Kontrak method CRUD                                  |
| service/Impl | KtpServiceImpl      | Class     | Implementasi logika bisnis & validasi                |
| util         | ValidationUtil      | Class     | Validasi data request menggunakan Bean Validation    |

---

## 🗄 Database Schema

**Database:** `spring`
**Tabel:** `ktp`

| Kolom         | Tipe         | Constraint                  |
|---------------|--------------|-----------------------------|
| id            | INT          | PRIMARY KEY, AUTO_INCREMENT |
| nomor_ktp     | VARCHAR(16)  | NOT NULL, UNIQUE            |
| nama_lengkap  | VARCHAR(100) | NOT NULL                    |
| alamat        | VARCHAR(255) | NOT NULL                    |
| tanggal_lahir | DATE         | NOT NULL                    |
| jenis_kelamin | VARCHAR(20)  | NOT NULL                    |

### SQL Script

```sql
CREATE DATABASE IF NOT EXISTS spring;
USE spring;

CREATE TABLE IF NOT EXISTS ktp (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nomor_ktp VARCHAR(16) NOT NULL UNIQUE,
    nama_lengkap VARCHAR(100) NOT NULL,
    alamat VARCHAR(255) NOT NULL,
    tanggal_lahir DATE NOT NULL,
    jenis_kelamin VARCHAR(20) NOT NULL
);
```

> **Catatan:** Tabel otomatis dibuat oleh Hibernate (`spring.jpa.hibernate.ddl-auto=update`) saat aplikasi pertama kali dijalankan. Cukup buat database `spring` secara manual.

## 📡 API Documentation

Base URL: `http://localhost:8080`

### Endpoints

| Method | Endpoint   | Keterangan                    | Request Body |
|--------|------------|-------------------------------|--------------|
| POST   | /ktp       | Tambah data KTP baru          | Ya           |
| GET    | /ktp       | Ambil semua data KTP          | Tidak        |
| GET    | /ktp/{id}  | Ambil data KTP berdasarkan ID | Tidak        |
| PUT    | /ktp/{id}  | Update data KTP               | Ya           |
| DELETE | /ktp/{id}  | Hapus data KTP                | Tidak        |

### 1. POST /ktp — Tambah Data KTP

**Request Body:**
```json
{
    "nomorKtp": "3201234567890001",
    "namaLengkap": "Budi Santoso",
    "alamat": "Jl. Merdeka No. 10, Jakarta",
    "tanggalLahir": "1990-05-15",
    "jenisKelamin": "Laki-laki"
}
```

**Response (201 Created):**
```json
{
    "status": "success",
    "data": {
        "id": 1,
        "nomorKtp": "3201234567890001",
        "namaLengkap": "Budi Santoso",
        "alamat": "Jl. Merdeka No. 10, Jakarta",
        "tanggalLahir": "1990-05-15",
        "jenisKelamin": "Laki-laki"
    }
}
```

### 2. GET /ktp — Ambil Semua Data KTP

**Response (200 OK):**
```json
{
    "status": "success",
    "data": [
        {
            "id": 1,
            "nomorKtp": "3201234567890001",
            "namaLengkap": "Budi Santoso",
            "alamat": "Jl. Merdeka No. 10, Jakarta",
            "tanggalLahir": "1990-05-15",
            "jenisKelamin": "Laki-laki"
        }
    ]
}
```

### 3. GET /ktp/{id} — Ambil Data KTP Berdasarkan ID

**Response (200 OK):**
```json
{
    "status": "success",
    "data": {
        "id": 1,
        "nomorKtp": "3201234567890001",
        "namaLengkap": "Budi Santoso",
        "alamat": "Jl. Merdeka No. 10, Jakarta",
        "tanggalLahir": "1990-05-15",
        "jenisKelamin": "Laki-laki"
    }
}
```

### 4. PUT /ktp/{id} — Perbarui Data KTP

**Request Body:**
```json
{
    "nomorKtp": "3201234567890001",
    "namaLengkap": "Budi Santoso Updated",
    "alamat": "Jl. Sudirman No. 99, Jakarta Selatan",
    "tanggalLahir": "1990-05-15",
    "jenisKelamin": "Laki-laki"
}
```

**Response (200 OK):**
```json
{
    "status": "success",
    "data": {
        "id": 1,
        "nomorKtp": "3201234567890001",
        "namaLengkap": "Budi Santoso Updated",
        "alamat": "Jl. Sudirman No. 99, Jakarta Selatan",
        "tanggalLahir": "1990-05-15",
        "jenisKelamin": "Laki-laki"
    }
}
```

### 5. DELETE /ktp/{id} — Hapus Data KTP

**Response (200 OK):**
```json
{
    "status": "success delete ktp with id 1"
}
```

### Error Handling

| Kasus                      | Exception                      | Pesan                          |
|----------------------------|--------------------------------|--------------------------------|
| Nomor KTP sudah terdaftar  | RuntimeException               | Nomor KTP sudah terdaftar      |
| Data tidak ditemukan       | RuntimeException               | Data KTP tidak ditemukan       |
| Validasi gagal             | ConstraintViolationException   | Detail field yang tidak valid  |

---

## 🖥 Frontend (Client-side)

Frontend dibuat menggunakan HTML, CSS, dan JavaScript dengan jQuery Ajax. Semua operasi CRUD dilakukan tanpa refresh halaman.

| File       | Lokasi                          | Keterangan                              |
|------------|----------------------------------|-----------------------------------------|
| index.html | `resources/static/index.html`   | Halaman utama (form & tabel)            |
| style.css  | `resources/static/css/style.css`| Styling & dark mode                     |
| app.js     | `resources/static/js/app.js`    | jQuery Ajax untuk CRUD operations       |
---

## 📸 Screenshot Tampilan Website

### Halaman Utama
> Tampilan halaman utama dengan form input dan tabel data KTP.


### Tambah Data KTP
> Form untuk menambahkan data KTP baru dengan input date dan dropdown jenis kelamin.


### Berhasil Tambah Data
> Pop-up notifikasi setelah data berhasil ditambahkan.


### Edit Data KTP
> Form terisi otomatis saat tombol Edit diklik.


### Berhasil Update Data
> Pop-up notifikasi setelah data berhasil diperbarui.


### Konfirmasi Hapus
> Modal pop-up konfirmasi sebelum data dihapus.


### Berhasil Hapus Data
> Toast notification setelah data berhasil dihapus.


### Dark Mode
> Tampilan dark mode.
