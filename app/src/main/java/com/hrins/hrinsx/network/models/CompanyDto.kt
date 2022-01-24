package com.example.example

import com.google.gson.annotations.SerializedName


data class CompanyDto (

  @SerializedName("name"           ) var name          : String       = "",
  @SerializedName("founder"        ) var founder       : String       = "",
  @SerializedName("founded"        ) var founded       : String          = "",
  @SerializedName("employees"      ) var employees     : Int          = 0,
  @SerializedName("vehicles"       ) var vehicles      : Int?          = null,
  @SerializedName("launch_sites"   ) var launchSites   : Int?          = null,
  @SerializedName("test_sites"     ) var testSites     : Int?          = null,
  @SerializedName("ceo"            ) var ceo           : String?       = null,
  @SerializedName("cto"            ) var cto           : String?       = null,
  @SerializedName("coo"            ) var coo           : String?       = null,
  @SerializedName("cto_propulsion" ) var ctoPropulsion : String?       = null,
  @SerializedName("valuation"      ) var valuation     : String        = "0",
  @SerializedName("headquarters"   ) var headquartersDto  : HeadquartersDto? = HeadquartersDto(),
  @SerializedName("links"          ) var companyLinksDto         : CompanyLinksDto?        = CompanyLinksDto(),
  @SerializedName("summary"        ) var summary       : String?       = null

)