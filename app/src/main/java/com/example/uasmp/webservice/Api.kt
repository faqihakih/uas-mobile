package com.example.uasmp.webservice

import com.example.uasmp.models.User
import com.example.uasmp.models.Wisata
import com.example.uasmp.utilities.Utils
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

import java.util.concurrent.TimeUnit

class Api{
    companion object{
        private var retrofit: Retrofit? = null

        private var okHttpClient = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()

        fun instance(): APIService = getClient().create(APIService::class.java)

        private fun getClient(): Retrofit {
            return if(retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(Utils.API_ENDPOINT)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            }else{
                retrofit!!
            }
        }
    }

}
interface APIService {

    @FormUrlEncoded
    @POST("auth/login")
    fun login(
            @Field("email") email: String? = null,
            @Field("password") password: String? = null
    ): Call<WrappedResponse<User>>

    @FormUrlEncoded
    @POST("auth/register")
    fun regis(
            @Field("name") name: String? = null,
            @Field("email") email: String? = null,
            @Field("password") password: String? = null,
            @Field("conf_password") conf_password: String? = null
    ): Call<WrappedResponse<User>>

    @GET("tourism")
    fun get(@Header("x-access-token") token : String) : Call<WrappedListResponse<Wisata>>

    @FormUrlEncoded
    @POST("tourism")
    fun createData(
            @Header("x-access-token") token :String,
            @Field("name") name: String? = null,
            @Field("location") location: String? = null,
            @Field("description") description: String? = null
    ): Call<WrappedResponse<Wisata>>
}
data class WrappedResponse<T> (
        @SerializedName("message") var message : String?,
        @SerializedName("status") var status : Boolean,
        @SerializedName("data") var data : T?
){
    constructor() : this(null, false, null)
}
data class WrappedListResponse<T> (
        @SerializedName("message") var message : String?,
        @SerializedName("status") var status : Boolean,
        @SerializedName("data") var data : List<T>
) {
    constructor() : this(null, false, listOf())
}