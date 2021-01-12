package com.example.uasmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uasmp.adapter.ImageAdapter


class WisataActivity : AppCompatActivity() {

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wisata)

        val imageList = listOf<Image>(
            Image(
                R.drawable.pai,
                "Wisata Pantai Alam Indah Kota Tegal ",
                ""


            ),
            Image(
                R.drawable.guci,
                "Wisata GUCI Kabupaten Tegal",
                ""
            ),Image(
                R.drawable.waterpark,
                "Gerbang Mas Bahari",
                " "
            )

        )


        val recyclerView = findViewById<RecyclerView>(R.id._imageRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ImageAdapter(this, imageList){
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }
    }


}