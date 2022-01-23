package com.hrins.hrinsx.network.models

import com.google.gson.annotations.SerializedName


data class LaunchFailureDetailsDto (

  @SerializedName("time"     ) var time     : Int?    = null,
  @SerializedName("altitude" ) var altitude : String? = null,
  @SerializedName("reason"   ) var reason   : String? = null

)