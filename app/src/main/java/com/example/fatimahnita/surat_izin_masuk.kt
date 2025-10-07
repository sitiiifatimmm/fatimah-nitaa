package com.example.fatimahnita

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class surat_izin_masuk : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surat_izin_masuk)

        // Ambil komponen dari layout
        val etNama = findViewById<EditText>(R.id.etNama)
        val etKeterangan = findViewById<EditText>(R.id.etKeterangan)
        val btnKirim = findViewById<Button>(R.id.btnKirim)

        // Buat instance database
        val dbHelper = DatabaseSuratIzin(this)

        // Saat tombol "Kirim Surat" ditekan
        btnKirim.setOnClickListener {
            val namaInput = etNama.text.toString().trim()
            val keteranganInput = etKeterangan.text.toString().trim()

            if (namaInput.isEmpty() || keteranganInput.isEmpty()) {
                Toast.makeText(this, "Isi semua data terlebih dahulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Ambil tanggal hari ini
            val tanggalSekarang = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            // Simpan ke database
            val isInserted = dbHelper.insertPermit(namaInput, tanggalSekarang, keteranganInput)

            if (isInserted) {
                Toast.makeText(this, "Surat izin berhasil disimpan!", Toast.LENGTH_LONG).show()

                // Setelah berhasil, pindah ke daftar surat
                val intent = Intent(this, beranda::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Gagal menyimpan surat izin!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
