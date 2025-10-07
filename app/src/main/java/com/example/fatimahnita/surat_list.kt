package com.example.fatimahnita

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class surat_list : AppCompatActivity() {

    companion object {
        val suratAdapter = mutableListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surat_list)

        val listView = findViewById<ListView>(R.id.listViewSurat)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, suratAdapter)
        listView.adapter = adapter
    }
}
