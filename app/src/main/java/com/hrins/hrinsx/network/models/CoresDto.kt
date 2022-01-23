package com.hrins.hrinsx.network.models

import com.google.gson.annotations.SerializedName


data class CoresDto (

  @SerializedName("core_serial"     ) var coreSerial     : String?  = null,
  @SerializedName("flight"          ) var flight         : Int?     = null,
  @SerializedName("block"           ) var block          : String?  = null,
  @SerializedName("gridfins"        ) var gridfins       : Boolean? = null,
  @SerializedName("legs"            ) var legs           : Boolean? = null,
  @SerializedName("reused"          ) var reused         : Boolean? = null,
  @SerializedName("land_success"    ) var landSuccess    : String?  = null,
  @SerializedName("landing_intent"  ) var landingIntent  : Boolean? = null,
  @SerializedName("landing_type"    ) var landingType    : String?  = null,
  @SerializedName("landing_vehicle" ) var landingVehicle : String?  = null

)