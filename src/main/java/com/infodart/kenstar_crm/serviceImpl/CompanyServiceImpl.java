package com.infodart.kenstar_crm.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.infodart.kenstar_crm.dto.CompanyDto;
import com.infodart.kenstar_crm.dto.UserDto;
import com.infodart.kenstar_crm.entity.Company;
import com.infodart.kenstar_crm.entity.Role;
import com.infodart.kenstar_crm.entity.User;
import com.infodart.kenstar_crm.repository.AuthRepository;
import com.infodart.kenstar_crm.repository.CompanyRepository;

import com.infodart.kenstar_crm.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public CompanyDto addCompany(CompanyDto companyDto) {
		List<Company> companyList = companyRepository.findAllByCompanyCode(companyDto.getCompanyCode());

		
		if (CollectionUtils.isEmpty(companyList)) {
			Company companyData = new Company();

			companyData.setCompanyId(companyDto.getCompanyId());
			companyData.setCompanyCode(companyDto.getCompanyCode());
			companyData.setCompanyName(companyDto.getCompanyName()); 

			 
			// userDetail.setPassword( passwordEncoder.encode(userDetailDto.getPassword())
			// );

			System.out.println("companyDto.toString() ::" + companyDto.toString());

//			String rolesAsString = String.join(", ", userDetailDto.getRole());
//
//			Role role = roleRepo.findByName(rolesAsString);
//			if (role == null) {
//				role = checkRoleExist();
//			}
//			userDetail.setRoles(Set.of(role));

			companyData.setCreatedBy("");
 			companyData.setUpdatedBy("");
 			 

 			companyRepository.save(companyData);
		} else {
			System.out.println("User already exist");
		}
		
		return companyDto;
	}

	@Override
	public List<CompanyDto> getAllCompany( ) {
		List<Company> roles = (List<Company>) companyRepository.findAll();

		List<CompanyDto> companyDtos = new ArrayList<>();
		for (Company role : roles) {
			CompanyDto comDto = new CompanyDto();
			comDto.setId(role.getId());
			comDto.setCompanyId(role.getCompanyId());
			comDto.setCompanyCode(role.getCompanyCode());
			comDto.setCompanyName(role.getCompanyName());

			companyDtos.add(comDto);
		}

		return companyDtos;
	}

	@Override
	public CompanyDto getCompany(CompanyDto companyDto) {
		List<Company> companyList = new ArrayList<>();
		if (!companyDto.getCompanyCode().isBlank()) {
			companyList = companyRepository.findAllByCompanyCode(companyDto.getCompanyCode());
		} else if (!companyDto.getCompanyName().isBlank()) {
			companyList = companyRepository.findAllByCompanyName(companyDto.getCompanyName());
		}

		CompanyDto companyDto2 = new CompanyDto();
		for (Company company : companyList) {

			companyDto2.setId(company.getId());
			companyDto2.setCompanyId(company.getCompanyId());
			companyDto2.setCompanyCode(company.getCompanyCode());
			companyDto2.setCompanyName(company.getCompanyName());
		}

		System.out.println(" getUser called 55 " + companyDto2.toString());
		return companyDto2;
	}

}
