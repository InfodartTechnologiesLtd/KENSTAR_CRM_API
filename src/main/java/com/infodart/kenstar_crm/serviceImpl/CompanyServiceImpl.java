package com.infodart.kenstar_crm.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.infodart.kenstar_crm.dto.CompanyDto;
import com.infodart.kenstar_crm.entity.Company;
import com.infodart.kenstar_crm.entity.User;
import com.infodart.kenstar_crm.enums.ERole;
import com.infodart.kenstar_crm.exceptions.IllegalArguException;
import com.infodart.kenstar_crm.exceptions.ResourceNotFoundException;
import com.infodart.kenstar_crm.mapper.CompanyMapper;
import com.infodart.kenstar_crm.repository.CompanyRepository;
import com.infodart.kenstar_crm.repository.UserRepository;
import com.infodart.kenstar_crm.service.CompanyService;

import jakarta.validation.Valid;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public List<CompanyDto> getAllCompany() {
		List<Company> roles = (List<Company>) companyRepository.findAll();

		List<CompanyDto> companyDtos = new ArrayList<>();
		for (Company role : roles) {
			CompanyDto comDto = new CompanyDto();
			comDto.setId(role.getId());
			// comDto.setCompanyId(role.getCompanyId());
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
			// companyDto2.setCompanyId(company.getCompanyId());
			companyDto2.setCompanyCode(company.getCompanyCode());
			companyDto2.setCompanyName(company.getCompanyName());
		}

		System.out.println(" getUser called 55 " + companyDto2.toString());
		return companyDto2;
	}

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @Autowired
	// private CompanyRepository companyRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CompanyMapper companyMapper;

	@Override
	public CompanyDto addCompany(Long userId, @Valid CompanyDto companyDto) {
		// Validate input fields manually before proceeding (additional validation, if
		// necessary)
		if (!StringUtils.hasText(companyDto.getCompanyName())) {
			throw new IllegalArguException("Company name is required.");
		}

		if (!StringUtils.hasText(companyDto.getCompanyCode())) {
			throw new IllegalArguException("Company code is required.");
		}

		if (companyDto.getCompanyCode().length() < 2 || companyDto.getCompanyCode().length() > 20) {
			throw new IllegalArguException("Company code must be between 2 and 20 characters.");
		}

		// Check if the user exists for the given userId
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isEmpty()) {
			throw new ResourceNotFoundException("User with ID " + userId + " not found.");
		}

		User user = userOptional.get();
		System.out.println("User role is : " + user.getRoles().getName());

		System.out.println("User role is 11 : " + ERole.ROLE_ADMIN);

		System.out.println("User role is 22 : " + user.getRoles().getName().equals(ERole.ROLE_ADMIN.name()));
		if (!user.getRoles().getName().equals(ERole.ROLE_ADMIN.name())) {
			throw new ResourceNotFoundException("User not allowed to create company.");
		}

		Optional<Company> companyCodeOptional = companyRepository.findByCompanyCode(companyDto.getCompanyCode());

		if (!companyCodeOptional.isEmpty()) {
			System.out.println("companyCodeData :: " + companyCodeOptional.get().getCompanyCode());
			throw new ResourceNotFoundException("Company already exist with this company code.");
		}

		Optional<Company> companyNameOptional = companyRepository.findByCompanyName(companyDto.getCompanyName());

		if (!companyNameOptional.isEmpty()) {
			System.out.println("companyNameData :: " + companyNameOptional.get().getCompanyName());
			throw new ResourceNotFoundException("Company already exist with this company Name.");
		}

		// Creating the company entity from the DTO
		Company company = new Company();
		company.setCompanyName(companyDto.getCompanyName());
		company.setCompanyCode(companyDto.getCompanyCode());
		company.setUser(userOptional.get()); // Link to the user

		// Saving the company in the database
		Company savedCompany = companyRepository.save(company);

		// Convert the saved company entity to DTO and return
		return companyMapper.toDto(savedCompany);
	}

	@Override
	public CompanyDto getCompanyById(Integer companyId) {
//        Company company = companyRepository.findById(companyId)
//                .orElseThrow(() -> new ResourceNotFoundException("Company with ID " + companyId + " not found."));

		// Find company by ID, it returns an Optional
		Optional<Company> companyOptional = companyRepository.findById(companyId);

		// Check if the company exists
		if (!companyOptional.isPresent()) {
			// If the company doesn't exist, throw a custom exception or handle it
			// accordingly
			throw new ResourceNotFoundException("Company with ID " + companyId + " not found");
		}

		// Get the Company entity from Optional
		Company company = companyOptional.get();
		return companyMapper.toDto(company);
	}

	@Override
	public CompanyDto updateCompany(Integer companyId, CompanyDto companyDto) {
		// Check if the company exists
//        Company company = companyRepository.findById(companyId)
//                .orElseThrow(() -> new ResourceNotFoundException("Company with ID " + companyId + " not found."));

		// Find company by ID, it returns an Optional
		Optional<Company> companyOptional = companyRepository.findById(companyId);

		// Check if the company exists
		if (!companyOptional.isPresent()) {
			// If the company doesn't exist, throw a custom exception or handle it
			// accordingly
			throw new ResourceNotFoundException("Company with ID " + companyId + " not found");
		}

		// Get the Company entity from Optional
		Company company = companyOptional.get();

		// Update the company details
		if (companyDto.getCompanyName() != null) {
			company.setCompanyName(companyDto.getCompanyName());
		}
		if (companyDto.getCompanyCode() != null) {
			company.setCompanyCode(companyDto.getCompanyCode());
		}

		// Save the updated company and return
		Company updatedCompany = companyRepository.save(company);
		return companyMapper.toDto(updatedCompany);
	}

	@Override
	public void deleteCompany(Integer companyId) {
//        Company company = companyRepository.findById(companyId)
//                .orElseThrow(() -> new ResourceNotFoundException("Company with ID " + companyId + " not found."));

		// Find company by ID, it returns an Optional
		Optional<Company> companyOptional = companyRepository.findById(companyId);

		// Check if the company exists
		if (!companyOptional.isPresent()) {
			// If the company doesn't exist, throw a custom exception or handle it
			// accordingly
			throw new ResourceNotFoundException("Company with ID " + companyId + " not found");
		}

		// Get the Company entity from Optional
		Company company = companyOptional.get();
		companyRepository.delete(company);
	}

	@Override
	public List<CompanyDto> getAllCompanies() {
		List<Company> companies = (List<Company>) companyRepository.findAll();
		return companyMapper.toDtoList(companies);
	}

}
