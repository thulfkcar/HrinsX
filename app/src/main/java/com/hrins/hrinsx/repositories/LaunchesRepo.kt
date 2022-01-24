package com.hrins.hrinsx.repositories
import com.android.volley.Request
import com.android.volley.Request.*
import com.google.gson.reflect.TypeToken
import com.hrins.hrinsx.App
import com.hrins.hrinsx.network.api.HttpHandler
import com.hrins.hrinsx.network.api.MultiResponseStructure
import com.hrins.hrinsx.network.api.OnCompleteListener
import com.hrins.hrinsx.network.models.LaunchDto
import java.lang.reflect.Type

class LaunchesRepo (private val httpHandler: HttpHandler,
                    private val app: App
){
    suspend fun launches(offset: Int,
                         sort: String,
                         order: String,
                         limit: Int,onCompleteListener:OnCompleteListener<List<LaunchDto>>) {
      //  val collectionType = object : TypeToken<Collection<LaunchDto?>?>() {}.type
        httpHandler.addRequest(
            "launches?offset=${offset}&sort=${sort}&order=${order}&limit=${limit}&id=true",
            "",
            object : TypeToken<Collection<LaunchDto?>?>() {}.type,
            Method.GET,
            "",
            onCompleteListener = onCompleteListener
        )
    }
}