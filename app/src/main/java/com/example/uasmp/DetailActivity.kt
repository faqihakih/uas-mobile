package com.example.uasmp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uasmp.contracts.WisataActiviyContract
import com.example.uasmp.models.Wisata
import com.example.uasmp.presenters.DeleteActivityPresenter
import com.example.uasmp.utilities.Utils
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), WisataActiviyContract.ViewDelete {
    private var presenter = DeleteActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val user = getUser()
        val nama = findViewById<TextView>(R.id.tvNamawisata)
        val lokasi = findViewById<TextView>(R.id.tvLocation)
        val keterangan = findViewById<TextView>(R.id.tvDescription)
        user?.let {
            nama.text=it.name
            lokasi.text=it.location
            keterangan.text=it.description
        }
        presenter = DeleteActivityPresenter(this)
        editData();
        Delete()
    }

    private fun editData(){
        btnEdit.setOnClickListener{
            val user = getUser()
            val intent = Intent(this, UpdateActivity::class.java)
            println("User "+ user)
            intent.putExtra("USER_ID", user?.id)
            println("KIYE ID NE WA BISA NE ORA KE JUKUT "+user?.id)
            intent.putExtra("NAME", user?.name)
            intent.putExtra("LOCATION", user?.location)
            intent.putExtra("DESCRIPTION", user?.description)

            startActivity(intent)
        }
    }

    private fun Delete(){
        btnDelete.setOnClickListener{
            val data = getUser();
            val id = data?.id.toString().toInt()
            val token = Utils.getToken(this)
            token?.let { it1 -> presenter.delete(id, it1) }
        }
    }

    private fun getUser() = intent.getParcelableExtra<Wisata>("wisata")
    override fun success(message: String?) {
            startActivity(Intent(this, WisataActivity::class.java))
            finish()
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    override fun isLoading(state: Boolean) {
        btnDelete.isEnabled = !state
    }
}