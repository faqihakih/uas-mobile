package com.example.uasmp.presenters

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.uasmp.contracts.WisataActivitiesContract
import com.example.uasmp.models.Wisata
import com.example.uasmp.utilities.Utils
import com.example.uasmp.webservice.Api
import com.example.uasmp.webservice.WrappedListResponse

class WisataActivitiesPresenter(v : WisataActivitiesContract.View?):WisataActivitiesContract.Interaction {
    private var view : WisataActivitiesContract.View? = v
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
                    if (body != null && body.status) {
                        view?.attachToRecycler(body.data)
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