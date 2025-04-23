package com.infodart.kenstar_crm.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.infodart.kenstar_crm.dto.CompanyDto;
import com.infodart.kenstar_crm.entity.Company;

@Component
public class CompanyMapper {

    // Convert Company entity to CompanyDto
    public CompanyDto toDto(Company company) {
        if (company == null) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();
        //companyDto.setCompanyId(company.getCompanyId());
        companyDto.setCompanyName(company.getCompanyName());
        companyDto.setCompanyCode(company.getCompanyCode());
        // Optionally, map other fields if needed
        // companyDto.setUserId(company.getUser().getUserId());

        return companyDto;
    }

    // Convert a list of Company entities to a list of CompanyDto
    public List<CompanyDto> toDtoList(List<Company> companies) {
        if (companies == null || companies.isEmpty()) {
            return null;
        }

        return companies.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Convert CompanyDto to Company entity
    public Company toEntity(CompanyDto companyDto) {
        if (companyDto == null) {
            return null;
        }

        Company company = new Company();
        //company.setCompanyId(companyDto.getCompanyId());
        company.setCompanyName(companyDto.getCompanyName());
        company.setCompanyCode(companyDto.getCompanyCode());
        // Optionally, map other fields if needed

        return company;
    }
}
