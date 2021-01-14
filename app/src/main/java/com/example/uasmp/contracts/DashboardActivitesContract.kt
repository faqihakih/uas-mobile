package com.example.uasmp.contracts

import com.example.uasmp.models.Wisata

interface DashboardActivitesContract {
    interface View {
        fun attachToRecycler(tourism : List<Wisata>)
        fun isLoading(state : Boolean)
        fun toast(message : String)
    }
    interface Interactor{
        fun destroy()
        fun allUser(api_token : String)
    }

}