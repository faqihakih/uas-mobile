package com.example.uasmp.contracts

import com.example.uasmp.models.Wisata

interface WisataActivitiesContract {
    interface View {
        fun attachToRecycler(tourism : List<Wisata>)
        fun isLoading(state : Boolean)
        fun toast(message : String)
    }
    interface CreateView {
        fun success(message: String)
        fun toast(message: String)
        fun isLoading(state: Boolean)
    }
    interface Interaction{
        fun allUser(token : String)
        fun destroy()
    }
    interface InteractionPost{
        fun validate(name: String, location: String, description: String) : Boolean
        fun save(token: String, name: String, location: String,description: String)
        fun destroy()
    }
}