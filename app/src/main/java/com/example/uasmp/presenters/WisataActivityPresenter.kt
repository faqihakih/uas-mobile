package com.example.uasmp.presenters

import android.widget.Toast
import com.example.uasmp.contracts.WisataActiviyContract
import com.example.uasmp.models.Wisata
import com.example.uasmp.webservice.Api
import com.example.uasmp.webservice.WrappedListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WisataActivityPresenter(v : WisataActiviyContract.View?) : WisataActiviyContract.Interaction {
    private var view : WisataActiviyContract.View? = v
    private var api = Api.instance()

    override fun allUser(token: String) {
        val request = api.get(token)
        request.enqueue(object : Callback<WrappedListResponse<Wisata>>{

            override fun onFailure(call: Call<WrappedListResponse<Wisata>>, t: Throwable) {
                println("Log: ${t.message} ")
                view?.toast("Cannot connect to server")
            }

            override fun onResponse(
                call: Call<WrappedListResponse<Wisata>>,
                response: Response<WrappedListResponse<Wisata>>
            ) {
                if(response.isSuccessful) {
                    val body = response.body()
                    println("response "+ response.body())
                    if (body != null && body.status) {
                        view?.attachToRecycle(body.data)
                    } else {
                        view?.toast("Something went wrong, try again later")
                    }
                }
            }
        })
    }

    override fun destroy() {
        view = null
    }
}