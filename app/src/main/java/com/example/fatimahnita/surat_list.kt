package com.example.fatimahnita

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SuratListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SuratAdapter
    private lateinit var fabTambah: FloatingActionButton
    private val suratList = mutableListOf<Surat>()
    private var idCounter = 1

    // Launcher untuk menerima data dari SuratIzinActivity
    private val tambahSuratLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val nama = data?.getStringExtra("nama") ?: ""
            val keterangan = data?.getStringExtra("keterangan") ?: ""
            if (nama.isNotEmpty() && keterangan.isNotEmpty()) {
                val suratBaru = Surat(idCounter++, nama, keterangan)
                suratList.add(suratBaru)
                adapter.notifyItemInserted(suratList.size - 1)
                Toast.makeText(this, "Surat berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surat_list)

        recyclerView = findViewById(R.id.recyclerViewSurat)
        fabTambah = findViewById(R.id.fabTambah)

        adapter = SuratAdapter(suratList,
            onEdit = { surat -> editSurat(surat) },
            onDelete = { surat -> hapusSurat(surat) }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fabTambah.setOnClickListener {
            val intent = Intent(this, SuratIzinActivity::class.java)
            tambahSuratLauncher.launch(intent)
        }
    }

    private fun hapusSurat(surat: Surat) {
        val posisi = suratList.indexOf(surat)
        suratList.remove(surat)
        adapter.notifyItemRemoved(posisi)
        Toast.makeText(this, "Surat dihapus", Toast.LENGTH_SHORT).show()
    }

    private fun editSurat(surat: Surat) {
        AlertDialog.Builder(this)
            .setTitle("Edit Surat")
            .setMessage("Fitur edit bisa diarahkan ke form edit (contoh: SuratIzinActivity dengan data lama)")
            .setPositiveButton("OK", null)
            .show()
    }
}
