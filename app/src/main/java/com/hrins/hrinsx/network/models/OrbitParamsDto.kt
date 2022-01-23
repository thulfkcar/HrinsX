package com.hrins.hrinsx.network.models

import com.google.gson.annotations.SerializedName


data class OrbitParamsDto (

  @SerializedName("reference_system"   ) var referenceSystem : String? = null,
  @SerializedName("regime"             ) var regime          : String? = null,
  @SerializedName("longitude"          ) var longitude       : String? = null,
  @SerializedName("semi_major_axis_km" ) var semiMajorAxisKm : String? = null,
  @SerializedName("eccentricity"       ) var eccentricity    : String? = null,
  @SerializedName("periapsis_km"       ) var periapsisKm     : Int?    = null,
  @SerializedName("apoapsis_km"        ) var apoapsisKm      : Int?    = null,
  @SerializedName("inclination_deg"    ) var inclinationDeg  : Int?    = null,
  @SerializedName("period_min"         ) var periodMin       : String? = null,
  @SerializedName("lifespan_years"     ) var lifespanYears   : String? = null,
  @SerializedName("epoch"              ) var epoch           : String? = null,
  @SerializedName("mean_motion"        ) var meanMotion      : String? = null,
  @SerializedName("raan"               ) var raan            : String? = null,
  @SerializedName("arg_of_pericenter"  ) var argOfPericenter : String? = null,
  @SerializedName("mean_anomaly"       ) var meanAnomaly     : String? = null

)