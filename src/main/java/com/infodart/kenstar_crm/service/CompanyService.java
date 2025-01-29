package com.infodart.kenstar_crm.service;

import java.util.List;

import com.infodart.kenstar_crm.dto.CompanyDto;
import com.infodart.kenstar_crm.dto.PinDto;
import com.infodart.kenstar_crm.dto.UserDto;

public interface CompanyService {

	 
	
	public CompanyDto addCompany(CompanyDto companyDto);
	public List<CompanyDto> getAllCompany();
	
	public CompanyDto getCompany(CompanyDto companyDto);
	
	 
	
}
