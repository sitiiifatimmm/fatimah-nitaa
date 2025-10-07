package com.example.fatimahnita

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class beranda : AppCompatActivity() {

    private lateinit var listViewSurat: ListView
    private lateinit var dbHelper: DatabaseSuratIzin
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        val btnSuratIzin: Button = findViewById(R.id.btnSuratIzin)
        val btnLogout: Button = findViewById(R.id.btnLogout)
        listViewSurat = findViewById(R.id.listViewSurat)

        // 🔹 Inisialisasi database
        dbHelper = DatabaseSuratIzin(this)

        // 🔹 Ambil semua data dari database
        val daftarPermit = dbHelper.getAllPermit()

        // 🔹 Ubah data menjadi string yang bisa ditampilkan di ListView
        val daftarString = daftarPermit.map { permit ->
            "Nama: ${permit["name"]}\nTanggal: ${permit["date"]}\nCatatan: ${permit["note"]}"
        }

        // 🔹 Buat adapter untuk ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, daftarString)
        listViewSurat.adapter = adapter

        // 🔸 Klik untuk Edit surat
        listViewSurat.setOnItemClickListener { _, _, position, _ ->
            val selectedPermit = daftarPermit[position]
           // val intent = Intent(this, EditSuratIzinActivity::class.java)
            intent.putExtra("PERMIT_ID", selectedPermit["id"])
            startActivity(intent)
        }

        // 🔸 Klik lama untuk Hapus surat
        listViewSurat.setOnItemLongClickListener { _, _, position, _ ->
            val selectedPermit = daftarPermit[position]
            val id = selectedPermit["id"]

            AlertDialog.Builder(this)
                .setTitle("Hapus Surat Izin")
                .setMessage("Yakin ingin menghapus surat ini?")
                .setPositiveButton("Ya") { _, _ ->
                    dbHelper.deletePermit(id!!.toInt())
                    Toast.makeText(this, "Surat dihapus", Toast.LENGTH_SHORT).show()
                    recreate() // Refresh halaman
                }
                .setNegativeButton("Batal", null)
                .show()
            true
        }

        // Tombol buat surat
        btnSuratIzin.setOnClickListener {
            val intent = Intent(this, surat_izin_masuk::class.java)
            startActivity(intent)
        }

        // Tombol logout
        btnLogout.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
            finish()
        }
    }
}
