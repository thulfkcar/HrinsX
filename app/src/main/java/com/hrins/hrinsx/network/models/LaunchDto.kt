package com.hrins.hrinsx.network.models

import com.google.gson.annotations.SerializedName


data class LaunchDto (
  @SerializedName("_id"                     ) var id                    : String?           = null,
  @SerializedName("flight_number"           ) var flightNumber          : Int?                  = null,
  @SerializedName("mission_name"            ) var missionName           : String?               = null,
  @SerializedName("mission_id"              ) var missionId             : ArrayList<String>     = arrayListOf(),
  @SerializedName("upcoming"                ) var upcoming              : Boolean?              = null,
  @SerializedName("launch_year"             ) var launchYear            : String?               = null,
  @SerializedName("launch_date_unix"        ) var launchDateUnix        : Int?                  = null,
  @SerializedName("launch_date_utc"         ) var launchDateUtc         : String?               = null,
  @SerializedName("launch_date_local"       ) var launchDateLocal       : String?               = null,
  @SerializedName("is_tentative"            ) var isTentative           : Boolean?              = null,
  @SerializedName("tentative_max_precision" ) var tentativeMaxPrecision : String?               = null,
  @SerializedName("tbd"                     ) var tbd                   : Boolean?              = null,
  @SerializedName("launch_window"           ) var launchWindow          : Int?                  = null,
  @SerializedName("rocket"                  ) var rocketDto                : RocketDto?               = RocketDto(),
  @SerializedName("ships"                   ) var ships                 : ArrayList<String>     = arrayListOf(),
  @SerializedName("telemetry"               ) var telemetryDto             : TelemetryDto?            = TelemetryDto(),
  @SerializedName("launch_site"             ) var launchSiteDto            : LaunchSiteDto?           = LaunchSiteDto(),
  @SerializedName("launch_success"          ) var launchSuccess         : Boolean              = false,
  @SerializedName("launch_failure_details"  ) var launchFailureDetailsDto  : LaunchFailureDetailsDto? = LaunchFailureDetailsDto(),
  @SerializedName("links"                   ) var linksDto                 : LinksDto                = LinksDto(),
  @SerializedName("details"                 ) var details               : String?               = null,
  @SerializedName("static_fire_date_utc"    ) var staticFireDateUtc     : String?               = null,
  @SerializedName("static_fire_date_unix"   ) var staticFireDateUnix    : Int?                  = null,
  @SerializedName("timeline"                ) var timelineDto              : TimelineDto?             = TimelineDto()

)