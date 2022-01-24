package com.hrins.hrinsx.repositories

import com.android.volley.Request.Method
import com.google.gson.reflect.TypeToken
import com.hrins.hrinsx.App
import com.hrins.hrinsx.network.api.HttpHandler
import com.hrins.hrinsx.network.api.OnCompleteListener
import com.hrins.hrinsx.network.models.LaunchDto

class LaunchesRepo(
    private val httpHandler: HttpHandler,
    private val app: App
) {
    suspend fun launches(
        offset: Int,
        sort: String?,
        order: String?,
        limit: Int,
        successLandingInCore: Boolean?,
        launchYear: Int?,

        onCompleteListener: OnCompleteListener<List<LaunchDto>>
    ) {
        //  val collectionType = object : TypeToken<Collection<LaunchDto?>?>() {}.type
        var route = "launches" +
                "?offset=${offset}" +
                "&limit=${limit}&id=true"
        if (sort != null)
            route += "&sort=${sort}"
        if (order != null)
            route += "&order=${order}"
        if (launchYear != null)
            route += "&launch_year=${launchYear}"
        if (successLandingInCore != null)
            route += "&land_success=${successLandingInCore}"

        httpHandler.addRequest(
            route,
            "",
            object : TypeToken<Collection<LaunchDto?>?>() {}.type,
            Method.GET,
            "",
            onCompleteListener = onCompleteListener
        )
    }
}