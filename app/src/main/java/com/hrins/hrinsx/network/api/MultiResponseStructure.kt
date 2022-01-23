package com.hrins.hrinsx.network.api

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

class MultiResponseStructure<T> {
    var data: List<T>? = null
    inline fun <reified T> validate(): T {
        return Gson().fromJson<T>(Gson().toJson(this), object : TypeToken<T>() {}.type)
    }
}