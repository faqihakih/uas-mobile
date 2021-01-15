package com.example.uasmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uasmp.contracts.WisataActiviyContract
import com.example.uasmp.presenters.CreateActivityPresenter
import com.example.uasmp.utilities.Utils
import kotlinx.android.synthetic.main.activity_tambah.*

class TambahActivity : AppCompatActivity(), WisataActiviyContract.ViewCreate {
    private var presenter = CreateActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)
        presenter = CreateActivityPresenter(this)
        Save()
    }

    private fun Save(){
        buttonAddWisata.setOnClickListener{
            val token = Utils.getToken(this)
            val name = editNamaWisata.text.toString().trim()
            val location  = editLokasi.text.toString().trim()
            val description = editDeskripsi.text.toString().trim()

            if(name.isNotEmpty() && location.isNotEmpty() && description.isNotEmpty()){
                token?.let { it1 -> presenter.save(it1, name, location, description) }
            }else{
                toast("Isi Semua form")
            }
        }
    }


    override fun success(message: String?) {
        startActivity(Intent(this, MainActivity::class.java))
                .also { finish() }
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    override fun isLoading(state: Boolean) {
        buttonAddWisata.isEnabled = !state
    }
}