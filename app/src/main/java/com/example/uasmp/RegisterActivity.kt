package com.example.uasmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uasmp.contracts.LoginActivityContract
import com.example.uasmp.presenters.RegisActivityPresenter
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_tambah.*

class RegisterActivity : AppCompatActivity(), LoginActivityContract.ViewCreate {
    private var presenter = RegisActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter = RegisActivityPresenter(this)
        Save()

        formLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun Save(){
        buttonRegis.setOnClickListener{
            val name = editNama.text.toString().trim()
            val email  = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()
            val conf_password = editPasswordConf.text.toString().trim()

            if(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && conf_password.isNotEmpty()){
                presenter.save(name, email, password, conf_password)
            }else{
                toast("Isi Semua form")
            }
        }
    }


    override fun success(message: String?) {
        startActivity(Intent(this, LoginActivity::class.java))
                .also { finish() }
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    override fun isLoading(state: Boolean) {
        buttonAddWisata.isEnabled = !state
    }
}