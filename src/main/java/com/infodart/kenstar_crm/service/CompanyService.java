package com.infodart.kenstar_crm.service;

import java.util.List;

import com.infodart.kenstar_crm.dto.CompanyDto;

public interface CompanyService {

	public List<CompanyDto> getAllCompany();

	public CompanyDto getCompany(CompanyDto companyDto);

	CompanyDto addCompany(Long userId, CompanyDto companyDto);

	public CompanyDto getCompanyById(Integer companyId);

	List<CompanyDto> getAllCompanies();

	public CompanyDto updateCompany(Integer companyId, CompanyDto companyDto);

	void deleteCompany(Integer companyId);

}
