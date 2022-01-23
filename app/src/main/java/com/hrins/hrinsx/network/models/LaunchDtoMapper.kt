package com.hrins.hrinsx.network.models

import com.hrins.hrinsx.domain.DomainMapper
import com.hrins.hrinsx.domain.Launch
import com.hrins.hrinsx.domain.Rocket

class LaunchDtoMapper : DomainMapper<LaunchDto, Launch> {
    override fun mapToDomainModel(model: LaunchDto): Launch {

        return Launch(
            1,
            "alpha 1",
            "2020-01,12",
            Rocket("345345-5345-534", "FireBall", "Falcon"),
            "from now 3 days",
            "https://images2.imgbox.com/40/e3/GypSkayF_o.png"
        )
    }

    override fun mapFromDomainModel(domainModel: Launch): LaunchDto {


        return LaunchDto()
    }

    fun mapToListOfDomain(launchesDto: List<LaunchDto>): List<Launch> {

        return launchesDto.map { mapToDomainModel(it) }
    }

}