package com.infodart.kenstar_crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.dto.CompanyDto;
import com.infodart.kenstar_crm.dto.UserDto;
import com.infodart.kenstar_crm.service.AuthService;
import com.infodart.kenstar_crm.service.CompanyService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/companyManage")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;

	
	@PostMapping(value = "/addCompany")
	public CompanyDto addCompany(@RequestBody CompanyDto companyDto) {
		return companyService.addCompany(companyDto);
	}

	
	@PostMapping(value = "/getCompany")
	public CompanyDto getCompany(@RequestBody CompanyDto userDetailDto) {
		return companyService.getCompany(userDetailDto);
	}
	
	@GetMapping(value = "/getAllCompany")
	public List<CompanyDto> getAllCompany() {
		return companyService.getAllCompany();
	}
}
