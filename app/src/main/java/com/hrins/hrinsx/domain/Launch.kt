package com.hrins.hrinsx.domain

data class Launch(
    val id: Int,
    val mission: String,
    val time: String,
    val rocket: Rocket,
    val launchDate: String,
    val image:String
)