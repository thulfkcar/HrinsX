package com.hrins.hrinsx.domain

import com.hrins.hrinsx.network.models.LinksDto

data class Launch(
    val id: String,
    val mission: String,
    val time: String,
    val rocket: Rocket,
    val launchDate: String,
    val image: String? = "https://images2.imgbox.com/40/e3/GypSkayF_o.png",
    val launchIsSuccessful: Boolean,
    val links: LinksDto?
)