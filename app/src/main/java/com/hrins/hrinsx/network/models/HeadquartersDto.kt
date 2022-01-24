package com.example.example

import com.google.gson.annotations.SerializedName


data class HeadquartersDto (

  @SerializedName("address" ) var address : String? = null,
  @SerializedName("city"    ) var city    : String? = null,
  @SerializedName("state"   ) var state   : String? = null

)