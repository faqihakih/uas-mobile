package com.example.uasmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import com.example.uasmp.contracts.LoginActivitiesContract
import com.example.uasmp.presenters.LoginActivitiesPresenter
import com.example.uasmp.utilities.Utils

class LoginActivity : AppCompatActivity(), LoginActivitiesContract.View {
    private var presenter  = LoginActivitiesPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        isLoggedIn()
        Login()

        buttonRegis.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    private fun Login(){
        buttonLogin.setOnClickListener{
            val email = editEmail.text.toString().trim()
            val pass = editPassword.text.toString().trim()
            if (email.isNotEmpty() && pass.isNotEmpty()){
                if (pass.length > 5 ){
                    presenter.login(email, pass)
                }else {
                    toast("Cek password anda")
                }
            }else{
                toast("isi semua form")

            }
        }
    }
    override fun toast(message: String) = Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()
    override fun success(token: String) {
        Utils.setToken(this, token)
        println(token)
        startActivity(Intent(this, MainActivity::class.java)).also { finish() }
    }
    override fun isLoading(state: Boolean) {
        buttonLogin.isEnabled = !state
    }
    override fun idError(err: String?) {editEmail.error = err}
    override fun passwordError(err: String?) {editPassword.error = err}
    override fun notConect() {buttonLogin.isEnabled = true
    }
    private fun isLoggedIn(){
        val token = Utils.getToken(this)
        if(token != null){
            startActivity(Intent(this, MainActivity::class.java)).also { finish() }
        }
    }

}
