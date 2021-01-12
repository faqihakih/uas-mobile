package com.example.uasmp.webservice

import com.example.uasmp.models.User
import com.example.uasmp.utilities.Utils
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

import java.util.concurrent.TimeUnit

class Api{
    companion object{
        private var retrofit: Retrofit? = null

        private var okHttpClient = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()

        fun instance() : APIService = getClient().create(APIService::class.java)

        private fun getClient(): Retrofit {
            return if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(Utils.API_ENDPOINT).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            } else{
                retrofit!!
            }
        }

    }

}
interface APIService {

    @FormUrlEncoded
    @POST("auth/login")
    fun login(@Field("email") email: String? = null, @Field("password") password: String? = null) : Call<WrappedResponse<User>>

    @FormUrlEncoded
    @GET("tourism")
//    fun getdata(@Field("name")name:String?=null, @Field("location")location:String?=null,@Field("description")description:String?=null)
    fun getPost(): Call<ArrayList<User>>
}
data class WrappedResponse<T> (
        @SerializedName("message") var message : String?,
        @SerializedName("status") var status : Boolean,
        @SerializedName("data") var data : T?
){
    constructor() : this(null, false, null)
}
