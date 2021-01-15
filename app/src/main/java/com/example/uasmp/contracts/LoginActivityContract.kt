package com.example.uasmp.contracts

interface LoginActivityContract {
    interface View{
        fun toast(message: String)
        fun success(token: String, user_id: String)
        fun isLoading(state : Boolean)
        fun isError(err: String?)
        fun passwordError(err: String?)
        fun notConnect()
    }

    interface ViewCreate{
        fun success(message: String?)
        fun toast(message : String?)
        fun isLoading(state : Boolean)
    }

    interface Interaction{
        fun validate(id: String, password: String) : Boolean
        fun login(email: String, password: String)
        fun destroy()
    }

    interface InteractionPost{
        fun validate(name: String, email: String, password: String, conf_password: String) : Boolean
        fun save(name: String, email: String, password: String, conf_password: String)
        fun destroy()
    }
}