package com.hrins.hrinsx.network.api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SingleResponseStructure<T> {
    var status: Boolean? = null
    var message: String? = null
    var data: T? = null
    var loading = false


    inline fun <reified T> validate(): T {

        return Gson().fromJson<T>(Gson().toJson(this), object : TypeToken<T>() {}.type)
    }

}