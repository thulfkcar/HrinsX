package com.hrins.hrinsx.network.models

import com.hrins.hrinsx.App
import com.hrins.hrinsx.Utils
import com.hrins.hrinsx.domain.DomainMapper
import com.hrins.hrinsx.domain.Launch
import com.hrins.hrinsx.domain.Rocket

class LaunchDtoMapper : DomainMapper<LaunchDto, Launch> {
    override fun mapToDomainModel(model: LaunchDto): Launch {
        val utils=Utils(App.instance)

        val days=utils.getDaysOf(model.launchDateUnix)
        val timeAgo = utils.getTimeAgo(model.launchDateUnix)
        return Launch(
            model.id!!,
            model.missionName!!,
            timeAgo,
            Rocket(
                model.rocketDto?.rocketId!!, model.rocketDto?.rocketName!!,
                model.rocketDto!!.rocketType!!
            ),
            days,
            model.linksDto.missionPatch,
            model.launchSuccess
        )
    }

    override fun mapFromDomainModel(domainModel: Launch): LaunchDto {


        return LaunchDto()
    }

    fun mapToListOfDomain(launchesDto: List<LaunchDto>): List<Launch> {

        return launchesDto.map { mapToDomainModel(it) }
    }

}