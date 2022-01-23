package com.hrins.hrinsx.network.api

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

class MultiResponseStructure<T> {
    var status: Boolean? = null
    var data: List<T>? = null
    var message: String? = null

    @SerializedName(value = "current_page", alternate = ["currentPage"])
    var currentPage = 0

    @SerializedName(value = "max_page", alternate = ["totalPages"])
    var totalPages = 0


    inline fun <reified T> validate(): T {

        return Gson().fromJson<T>(Gson().toJson(this), object : TypeToken<T>() {}.type)
    }
}