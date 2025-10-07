package com.example.fatimahnita

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class beranda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)


        val btnSuratIzin: Button = findViewById(R.id.btnSuratIzin)
        btnSuratIzin.setOnClickListener {
            val intent = Intent(this, surat_izin_masuk::class.java)
            startActivity(intent)
        }

        // Tombol logout
        val btnLogout: Button = findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
            finish()
        }
    }
}
