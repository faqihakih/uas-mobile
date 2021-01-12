package com.example.uasmp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    @SerializedName("id_user") var user_code : String? = null,
    @SerializedName("name") var name : String? = null,
    @SerializedName("username") var status : String? = null,
    @SerializedName("email") var email : String? = null,
    @SerializedName("token") var token : String? = null,
    @SerializedName("location") var location : String? = null,
    @SerializedName("description") var description : String? = null
): Parcelable