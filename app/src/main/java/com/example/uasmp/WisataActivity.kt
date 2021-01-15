package com.example.uasmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uasmp.adapter.WisataAdapter
import com.example.uasmp.contracts.WisataActiviyContract
import com.example.uasmp.models.Wisata
import com.example.uasmp.presenters.WisataActivityPresenter
import com.example.uasmp.utilities.Utils
import kotlinx.android.synthetic.main.activity_wisata.*

class WisataActivity : AppCompatActivity(), WisataActiviyContract.View {
    private var presenter = WisataActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wisata)
        checkIsLoggedIn()
        presenter = WisataActivityPresenter(this)
        Logout()
        intentCreate()
    }

    private fun intentCreate(){
//        btnCreate.setOnClickListener{
//            startActivity(Intent(this, CreateTourismActivity::class.java)).also { finish() }
//        }
    }

    private fun Logout(){
//        btnLogout.setOnClickListener{
//            startActivity(Intent(this, LoginActivity::class.java)).also{
//                finish()
//                WisataUtils.clearToken(this)
//            }
//        }
    }

    private fun getData (){
        Utils.getToken(this)?.let { presenter?.allUser(it) }
    }

    override fun attachToRecycle(tourism: List<Wisata>) {
        rvWisata.apply {
            layoutManager = LinearLayoutManager(this@WisataActivity)
            adapter = WisataAdapter(tourism, this@WisataActivity)
        }
    }

    override fun toast(message: String?) {
        Toast.makeText(this@WisataActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun isLoading(state: Boolean) {

    }

    override fun notConnect(message: String?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    private fun checkIsLoggedIn(){
        val token = Utils.getToken(this@WisataActivity)
        Toast.makeText(this, token, Toast.LENGTH_LONG).show()
        if(token == null || token.equals("UNDEFINED")){
            startActivity(Intent(this@WisataActivity, LoginActivity::class.java).also { finish() })
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }




}