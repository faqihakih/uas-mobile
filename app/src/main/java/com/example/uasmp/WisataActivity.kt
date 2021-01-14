package com.example.uasmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uasmp.adapter.ImageAdapter
import com.example.uasmp.adapter.WisataAdapter
import com.example.uasmp.contracts.WisataActivitiesContract
import com.example.uasmp.models.Wisata
import com.example.uasmp.presenters.WisataActivitiesPresenter
import com.example.uasmp.utilities.Utils
import kotlinx.android.synthetic.main.activity_wisata.*


class WisataActivity : AppCompatActivity(), WisataActivitiesContract.View {

    private var presenter = WisataActivitiesPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkIsLoggedIn()
        presenter = WisataActivitiesPresenter(this)
        Logout()
        intentCreate()
    }

    private fun intentCreate(){

    }

    private fun Logout(){

    }

    private fun getData (){
        Utils.getToken(this)?.let { presenter?.allUser(it) }
    }

    override fun attachToRecycler(tourism: List<Wisata>) {
        _imageRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@WisataActivity)
            adapter = WisataAdapter(tourism, this@WisataActivity)
        }
    }

    override fun isLoading(state: Boolean) {

    }

    override fun toast(message: String) {
        Toast.makeText(this@WisataActivity, message, Toast.LENGTH_LONG).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    private fun checkIsLoggedIn(){
        val token = Utils.getToken(this@WisataActivity)
        if(token == null || token.equals("UNDEFINED")){
            startActivity(Intent(this@WisataActivity, LoginActivity::class.java).also { finish() })
        }
    }


    override fun onResume() {
        super.onResume()
        getData()
    }



//    companion object{
//        val INTENT_PARCELABLE = "OBJECT_INTENT"
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_wisata)
//
//        val imageList = listOf<Image>(
//            Image(
//                R.drawable.pai,
//                "Wisata Pantai Alam Indah Kota Tegal ",
//                ""
//            ),
//            Image(
//                R.drawable.guci,
//                "Wisata GUCI Kabupaten Tegal",
//                ""
//            ),Image(
//                R.drawable.waterpark,
//                "Gerbang Mas Bahari",
//                " "
//            )
//
//        )
//
//
//        val recyclerView = findViewById<RecyclerView>(R.id._imageRecyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.adapter = ImageAdapter(this, imageList){
//            val intent = Intent(this, DetailActivity::class.java)
//            intent.putExtra(INTENT_PARCELABLE, it)
//            startActivity(intent)
//        }
//    }


}