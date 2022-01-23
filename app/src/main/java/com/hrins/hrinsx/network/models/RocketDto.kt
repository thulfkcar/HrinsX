package com.hrins.hrinsx.network.models

import com.google.gson.annotations.SerializedName


data class RocketDto (

  @SerializedName("rocket_id"    ) var rocketId    : String?      = null,
  @SerializedName("rocket_name"  ) var rocketName  : String?      = null,
  @SerializedName("rocket_type"  ) var rocketType  : String?      = null,
  @SerializedName("first_stage"  ) var firstStageDto  : FirstStageDto?  = FirstStageDto(),
  @SerializedName("second_stage" ) var secondStageDto : SecondStageDto? = SecondStageDto(),
  @SerializedName("fairings"     ) var fairingsDto    : FairingsDto?    = FairingsDto()

)