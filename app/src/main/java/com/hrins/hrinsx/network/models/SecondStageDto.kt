package com.hrins.hrinsx.network.models

import com.google.gson.annotations.SerializedName


data class SecondStageDto (

  @SerializedName("block"    ) var block    : Int?                = null,
  @SerializedName("payloads" ) var payloadsDto : ArrayList<PayloadsDto> = arrayListOf()

)