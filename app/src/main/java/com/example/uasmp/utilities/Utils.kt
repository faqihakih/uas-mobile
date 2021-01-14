package com.example.uasmp.utilities

import android.content.Context

class Utils {
    companion object {
        var API_ENDPOINT = "https://22664ebc967f.ngrok.io/"


        fun getToken(context: Context) : String? {
            val token = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            return token?.getString("TOKEN", null)
        }

        fun setToken(context : Context, token: String) {
            val pref = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            pref.edit().apply(){
                putString("TOKEN", token)
                apply()
            }
        }

        fun clearToken(context: Context){
            val pref = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            pref.edit().clear().apply()
        }

        fun isValidPassword(password : String) = password.length != null

    }
}