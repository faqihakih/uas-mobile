package com.example.uasmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uasmp.utilities.Utils
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_wisata.setOnClickListener {
            startActivity(Intent(this,WisataActivity::class.java))
        }
        btn_tentang.setOnClickListener {
            startActivity(Intent(this,TentangActivity::class.java))
        }
        btn_tambah.setOnClickListener {
            startActivity(Intent(this,TambahActivity::class.java))
        }
        buttonLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            Utils.clearToken(this)
        }
    }

}