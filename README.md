# Tugas CRUD KTP

Aplikasi CRUD data Kartu Tanda Penduduk (KTP) menggunakan Spring Boot sebagai REST API backend dan HTML/CSS/JavaScript dengan jQuery Ajax sebagai frontend client-side. Semua operasi CRUD berjalan tanpa refresh halaman menggunakan Ajax.

---

## Teknologi

- **Backend:** Spring Boot 4.0.3, Java 25
- **Database:** MySQL
- **ORM:** Spring Data JPA (Hibernate)
- **Frontend:** HTML5, CSS3, JavaScript, jQuery Ajax
- **Library:** MapStruct, Lombok, Validation
- **Build Tool:** Maven

---

## Struktur Package

```
com.deploy.praktikum2crudktp/
├── controller/       → REST Controller (handle API endpoint)
├── mapper/           → MapStruct interface (Entity ↔ DTO)
├── model/
│   ├── dto/          → Data Transfer Object (Request & Response)
│   └── entity/       → JPA Entity (mapping ke tabel database)
├── repository/       → Spring Data JPA Repository
├── service/          → Service interface
│   └── Impl/         → Service implementation (logika bisnis)
└── util/             → Utility class (validasi data)
```

| Package      | File                | Tipe      | Keterangan                                |
|--------------|---------------------|-----------|-------------------------------------------|
| controller   | KtpController       | Class     | Handle 5 endpoint CRUD                    |
| mapper       | KtpMapper           | Interface | Konversi otomatis Entity ke DTO           |
| model/dto    | KtpAddRequest       | Class     | DTO untuk request tambah & update data    |
| model/dto    | KtpDto              | Class     | DTO untuk response data KTP               |
| model/entity | Ktp                 | Class     | Entity JPA mapping ke tabel ktp           |
| repository   | KtpRepository       | Interface | Akses database dengan Spring Data JPA     |
| service      | KtpService          | Interface | Kontrak method CRUD                       |
| service/Impl | KtpServiceImpl      | Class     | Implementasi logika bisnis & validasi     |
| util         | ValidationUtil      | Class     | Validasi data request menggunakan Bean Validation |

---

## Database

**Schema:** `spring` | **Tabel:** `ktp`

| Kolom         | Tipe         | Constraint                  |
|---------------|--------------|-----------------------------|
| id            | INT          | PRIMARY KEY, AUTO_INCREMENT |
| nomor_ktp     | VARCHAR(16)  | NOT NULL, UNIQUE            |
| nama_lengkap  | VARCHAR(100) | NOT NULL                    |
| alamat        | VARCHAR(255) | NOT NULL                    |
| tanggal_lahir | DATE         | NOT NULL                    |
| jenis_kelamin | VARCHAR(20)  | NOT NULL                    |

Tabel otomatis dibuat oleh Hibernate (`spring.jpa.hibernate.ddl-auto=update`) saat aplikasi pertama kali dijalankan. Cukup buat database `spring` secara manual.

---

## API Documentation

Base URL: `http://localhost:8080`

### Endpoints

| Method | Endpoint   | Keterangan                    | Request Body |
|--------|------------|-------------------------------|--------------|
| POST   | /ktp       | Tambah data KTP baru          | Ya           |
| GET    | /ktp       | Ambil semua data KTP          | Tidak        |
| GET    | /ktp/{id}  | Ambil data KTP berdasarkan ID | Tidak        |
| PUT    | /ktp/{id}  | Update data KTP               | Ya           |
| DELETE | /ktp/{id}  | Hapus data KTP                | Tidak        |

### Contoh Request Body (POST & PUT)

```json
{
    "nomorKtp": "3201234567890001",
    "namaLengkap": "Budi Santoso",
    "alamat": "Jl. Merdeka No. 10, Jakarta",
    "tanggalLahir": "1990-05-15",
    "jenisKelamin": "Laki-laki"
}
```

### Contoh Response Sukses

**POST /ktp (201 Created):**
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

**DELETE /ktp/1 (200 OK):**
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

## Frontend (Client-side)

Frontend dibuat menggunakan HTML, CSS, dan JavaScript dengan jQuery Ajax. Semua operasi CRUD dilakukan tanpa refresh halaman.

| File       | Lokasi                          | Keterangan                              |
|------------|----------------------------------|-----------------------------------------|
| index.html | `resources/static/index.html`   | Halaman utama (form & tabel)            |
| style.css  | `resources/static/css/style.css`| Styling & dark mode                     |
| app.js     | `resources/static/js/app.js`    | jQuery Ajax untuk CRUD operations       |

## Screenshot

### Halaman Utama


### Tambah Data KTP


### Berhasil Tambah Data


### Edit Data KTP


### Berhasil Update Data


### Konfirmasi Hapus


### Berhasil Hapus Data

### Dark Mode
