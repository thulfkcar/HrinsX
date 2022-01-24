package com.hrins.hrinsx.network.models

import com.example.example.CompanyDto
import com.hrins.hrinsx.domain.Company
import com.hrins.hrinsx.domain.DomainMapper

class CompanyDtoMapper : DomainMapper<CompanyDto, Company> {
    override fun mapToDomainModel(model: CompanyDto): Company {

        return Company(model.name, model.founder, model.founded, model.employees, model.valuation)
    }

    override fun mapFromDomainModel(domainModel: Company): CompanyDto {
        TODO("Not yet implemented")
    }
}