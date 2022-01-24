package com.hrins.hrinsx.network.models

import com.google.gson.annotations.SerializedName


data class PayloadsDto (

  @SerializedName("payload_id"       ) var payloadId      : String?           = null,
  @SerializedName("norad_id"         ) var noradId        : ArrayList<String> = arrayListOf(),
  @SerializedName("reused"           ) var reused         : Boolean?          = null,
  @SerializedName("customers"        ) var customers      : ArrayList<String> = arrayListOf(),
  @SerializedName("nationality"      ) var nationality    : String?           = null,
  @SerializedName("manufacturer"     ) var manufacturer   : String?           = null,
  @SerializedName("payload_type"     ) var payloadType    : String?           = null,
  @SerializedName("payload_mass_kg"  ) var payloadMassKg  : Double?              = null,
  @SerializedName("payload_mass_lbs" ) var payloadMassLbs : Double?              = null,
  @SerializedName("orbit"            ) var orbit          : String?           = null,
  @SerializedName("orbit_params"     ) var orbitParamsDto    : OrbitParamsDto?      = OrbitParamsDto()

)