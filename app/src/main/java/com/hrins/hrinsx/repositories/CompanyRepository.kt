package com.hrins.hrinsx.repositories
import com.android.volley.Request
import com.android.volley.Request.*
import com.example.example.CompanyDto
import com.google.gson.reflect.TypeToken
import com.hrins.hrinsx.App
import com.hrins.hrinsx.network.api.HttpHandler
import com.hrins.hrinsx.network.api.MultiResponseStructure
import com.hrins.hrinsx.network.api.OnCompleteListener
import com.hrins.hrinsx.network.models.LaunchDto
import java.lang.reflect.Type

class CompanyRepository (private val httpHandler: HttpHandler,
                         private val app: App
){
    suspend fun info(onCompleteListener:OnCompleteListener<CompanyDto>) {
        httpHandler.addRequest(
            "info",
            "",
            object : TypeToken<CompanyDto?>() {}.type,
            Method.GET,
            "",
            onCompleteListener = onCompleteListener
        )
    }
}