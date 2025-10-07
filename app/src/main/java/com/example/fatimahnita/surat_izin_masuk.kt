package com.example.fatimahnita

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class surat_izin_masuk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surat_izin_masuk)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etKeterangan = findViewById<EditText>(R.id.etKeterangan)
        val btnKirim = findViewById<Button>(R.id.btnKirim)

        btnKirim.setOnClickListener {
            val namaInput = etNama.text.toString().trim()
            val keteranganInput = etKeterangan.text.toString().trim()

            if (namaInput.isEmpty() || keteranganInput.isEmpty()) {
                Toast.makeText(this, "Isi semua data terlebih dahulu!", Toast.LENGTH_SHORT).show()
            } else {
                // Simpan data ke daftar surat
                surat_list.daftarSurat.add("Nama: $namaInput\nKeterangan: $keteranganInput")

                Toast.makeText(this, "Surat izin berhasil dikirim!", Toast.LENGTH_LONG).show()

                // Pindah ke halaman list surat
                val intent = Intent(this, surat_list::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
