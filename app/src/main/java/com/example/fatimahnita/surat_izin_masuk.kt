package com.example.fatimahnita

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SuratIzinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surat_izin_masuk)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etKeterangan = findViewById<EditText>(R.id.etKeterangan)
        val btnKirim = findViewById<Button>(R.id.btnKirim)

        btnKirim.setOnClickListener {
            val nama = etNama.text.toString()
            val ket = etKeterangan.text.toString()

            if (nama.isNotEmpty() && ket.isNotEmpty()) {
                val resultIntent = Intent(this, SuratListActivity::class.java)
                resultIntent.putExtra("nama", nama)
                resultIntent.putExtra("keterangan", ket)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Isi semua field dulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
