package com.example.uasmp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uasmp.models.Wisata

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val user = getUser()
        val nama = findViewById<TextView>(R.id.tvNamaWisata)
        val lokasi = findViewById<TextView>(R.id.tvLocationWisata)
        val keterangan = findViewById<TextView>(R.id.tvDescriptionWisata)
        user?.let {
            nama.text=it.name
            lokasi.text=it.location
            keterangan.text=it.description
        }
    }
    private fun getUser() = intent.getParcelableExtra<Wisata>("wisata")
}