package com.example.uasmp.presenters

import com.example.uasmp.contracts.LoginActivityContract
import com.example.uasmp.models.User
import com.example.uasmp.webservice.Api
import com.example.uasmp.webservice.WrappedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisActivityPresenter(v: LoginActivityContract.ViewCreate): LoginActivityContract.InteractionPost {
    private var view : LoginActivityContract.ViewCreate? = v
    private var api = Api.instance()
    override fun validate(name: String, email: String, password: String, conf_password: String): Boolean {
        return true
    }

    override fun save(name: String, email: String, password: String, conf_password : String) {
        api.regis(name, email, password, conf_password).enqueue(object: Callback<WrappedResponse<User>>{
            override fun onFailure(call: Call<WrappedResponse<User>>, t: Throwable) {
                view?.toast("Koneksi tidak bisa")
            }

            override fun onResponse(
                    call: Call<WrappedResponse<User>>,
                    response: Response<WrappedResponse<User>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null && body.status){
                        println("body "+ body)
                        view?.success("Berhasil")
                    }

                }else{
                    view?.toast("Ada yang tidak beres, coba lagi nanti, atau hubungi admin")
                }
                view?.isLoading(false)
            }
        })
    }

    override fun destroy() {
        view = null
    }


}