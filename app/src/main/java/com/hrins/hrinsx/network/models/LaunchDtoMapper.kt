package com.hrins.hrinsx.network.models

import com.hrins.hrinsx.domain.DomainMapper
import com.hrins.hrinsx.domain.Launch
import com.hrins.hrinsx.domain.Rocket

class LaunchDtoMapper : DomainMapper<LaunchDto, Launch> {
    override fun mapToDomainModel(model: LaunchDto): Launch {

        return Launch(
            model.id!!,
            model.missionName!!,
            model.launchDateUtc!!,
            Rocket(model.rocketDto?.rocketId!!, model.rocketDto?.rocketName!!,
                model.rocketDto!!.rocketType!!
            ),
            model.launchDateUtc+model.launchYear,
            model.linksDto.missionPatch
        )
    }

    override fun mapFromDomainModel(domainModel: Launch): LaunchDto {


        return LaunchDto()
    }

    fun mapToListOfDomain(launchesDto: List<LaunchDto>): List<Launch> {

        return launchesDto.map { mapToDomainModel(it) }
    }

}