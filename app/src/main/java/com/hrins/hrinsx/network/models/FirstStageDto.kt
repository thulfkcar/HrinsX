package com.hrins.hrinsx.network.models

import com.google.gson.annotations.SerializedName


data class FirstStageDto (

  @SerializedName("cores" ) var cores : ArrayList<CoresDto> = arrayListOf()

)