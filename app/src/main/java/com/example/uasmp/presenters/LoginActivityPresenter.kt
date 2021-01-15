package com.example.wisataproject.presenters

import android.util.Log
import com.example.uasmp.contracts.LoginActivityContract
import com.example.uasmp.models.User
import com.example.uasmp.utilities.Utils
import com.example.uasmp.webservice.Api
import com.example.uasmp.webservice.WrappedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityPresenter (v : LoginActivityContract.View?) : LoginActivityContract.Interaction{
    private var view: LoginActivityContract.View? = v
    private var api = Api.instance()

    override fun validate(id: String, password: String): Boolean {
        view?. passwordError(null)
        if(!Utils.isValidPassword(password)){
            view?.passwordError("Password tidak valid")
            return false
        }

        return true
    }

    override fun login(email: String, password: String) {
        view?.isLoading(true)
        api.login(email, password).enqueue(object: Callback<WrappedResponse<User>>{
            override fun onFailure(call: Call<WrappedResponse<User>>, t: Throwable) {
                view?.toast("Koneksi tidak bisa")
                view?.notConnect()
            }

            override fun onResponse(
                call: Call<WrappedResponse<User>>,
                response: Response<WrappedResponse<User>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null && body.status){
                        view?.toast("Selamat datang ${body.data!!.name}")
                        println("body "+ body.data)
                        val token = body.data?.token!!
                        val id_user = body.data?.id_user!!
                        view?.success(token, id_user)
                        Log.d("onResponse: " ,token)
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

