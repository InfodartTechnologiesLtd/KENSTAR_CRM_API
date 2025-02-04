package com.infodart.kenstar_crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.dto.CompanyDto;
import com.infodart.kenstar_crm.dto.ResponseDto;
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
	public ResponseEntity<ResponseDto<CompanyDto>> addCompany(@RequestBody CompanyDto companyDto) {
		CompanyDto comDto = companyService.addCompany(companyDto);
		return ResponseEntity.ok(ResponseDto.success("200", "Company added", comDto));
	}

	@PostMapping(value = "/getCompany")
	public ResponseEntity<ResponseDto<CompanyDto>> getCompany(@RequestBody CompanyDto companyDto) {
		CompanyDto comDto = companyService.getCompany(companyDto);
		return ResponseEntity.ok(ResponseDto.success("200", "Company Data found", comDto));
	}

	@GetMapping(value = "/getAllCompany")
	public ResponseEntity<ResponseDto<List<CompanyDto>>> getAllCompany() {
		List<CompanyDto> companyList = companyService.getAllCompany();
		return ResponseEntity.ok(ResponseDto.success("200", "Company list found", companyList));
	}
}
