package com.example.uasmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.uasmp.contracts.WisataActiviyContract
import com.example.uasmp.presenters.UpdateActivityPresenter
import com.example.uasmp.utilities.Utils
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity(), WisataActiviyContract.ViewEdit {
    private var presenter = UpdateActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        etIdTourism.setText(intent.getStringExtra("USER_ID"))
        etName.setText(intent.getStringExtra("NAME"))
        etLocation.setText(intent.getStringExtra("LOCATION"))
        etDescription.setText(intent.getStringExtra("DESCRIPTION"))
        presenter = UpdateActivityPresenter(this)
        Update()
    }

    private fun Update(){
        btnUpdate.setOnClickListener{
            val token = Utils.getToken(this)
            val id = etIdTourism.text.toString().toInt();
            val name = etName.text.toString().trim()
            val location = etLocation.text.toString().trim()
            val description = etDescription.text.toString().trim();

            if(name.isNotEmpty() && location.isNotEmpty() && description.isNotEmpty()){
                token?.let { it1 -> presenter.update(id, it1, name, location, description) }
            }else{
                toast("Isi semua form")
            }
        }
    }

    override fun success(message: String?) {
            startActivity(Intent(this, WisataActivity::class.java))
            finish()
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    override fun isLoading(state: Boolean) {
        btnUpdate.isEnabled = !state
    }

}