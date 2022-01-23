package com.hrins.hrinsx.network.models

import com.google.gson.annotations.SerializedName


data class TelemetryDto (

  @SerializedName("flight_club" ) var flightClub : String? = null

)