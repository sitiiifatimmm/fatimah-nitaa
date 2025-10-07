package com.example.fatimahnita

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter

class surat_list : AppCompatActivity() {

    // Data surat disimpan di companion object biar tetap ada walau pindah activity
    companion object {
        val daftarSurat = mutableListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surat_list)

        val listView = findViewById<ListView>(R.id.listviewsurat)

        // Adapter untuk menampilkan data dalam ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, daftarSurat)
        listView.adapter = adapter
    }
}
