package com.hrins.hrinsx.network.models

import com.google.gson.annotations.SerializedName


data class FairingsDto (

  @SerializedName("reused"           ) var reused          : Boolean? = null,
  @SerializedName("recovery_attempt" ) var recoveryAttempt : Boolean? = null,
  @SerializedName("recovered"        ) var recovered       : Boolean? = null,
  @SerializedName("ship"             ) var ship            : String?  = null

)