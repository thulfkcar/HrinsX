package com.hrins.hrinsx.repositories

import com.hrins.hrinsx.App
import com.hrins.hrinsx.network.api.HttpHandler

class LaunchesRepo (private val httpHandler: HttpHandler,
                    private val app: App
){
    fun launches(page: Int, pageSize: Int, any: Any) {

    }
}