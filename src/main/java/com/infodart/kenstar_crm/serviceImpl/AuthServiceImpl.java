package com.infodart.kenstar_crm.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.infodart.kenstar_crm.dto.CompanyDto;
import com.infodart.kenstar_crm.dto.PinDto;
import com.infodart.kenstar_crm.dto.RoleDto;
import com.infodart.kenstar_crm.dto.UserDto;
import com.infodart.kenstar_crm.entity.Company;
import com.infodart.kenstar_crm.entity.Pin;
import com.infodart.kenstar_crm.entity.Role;
import com.infodart.kenstar_crm.entity.User;
import com.infodart.kenstar_crm.repository.AuthRepository;
import com.infodart.kenstar_crm.repository.CompanyRepository;
import com.infodart.kenstar_crm.repository.PinRepository;
import com.infodart.kenstar_crm.repository.RoleRepo;
import com.infodart.kenstar_crm.repository.UserRepository;
import com.infodart.kenstar_crm.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private PinRepository pinRepository;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto registerUser(UserDto userDetailDto) {
		List<User> userDetailList = authRepository.findAllByUsername(userDetailDto.getUsername());

		if (CollectionUtils.isEmpty(userDetailList)) {
			User userDetail = new User();

			userDetail.setEmail(userDetailDto.getEmail());
			userDetail.setMobilenumber(userDetailDto.getMobilenumber());
			userDetail.setUsername(userDetailDto.getUsername());

			userDetail.setPassword(userDetailDto.getPassword());
			// userDetail.setPassword( passwordEncoder.encode(userDetailDto.getPassword())
			// );

			System.out.println("userDetailDto.toString() ::" + userDetailDto.toString());

			String rolesAsString = String.join(", ", userDetailDto.getRole());

			//Role role =  roleRepo.findByName(rolesAsString);
			Optional<Role> optionalRole= roleRepo.findByName(rolesAsString);
			Role role =null;
			if (optionalRole.isEmpty()) {
				 role = new Role();
				role = checkRoleExist();
			}
			//userDetail.setRoles(Set.of(role));
			userDetail.setRoles(role);

			
			userDetail.setCreatedBy(userDetailDto.getCreatedBy());
			// userDetail.setCreatedDateTime(userDetailDto.getCreatedDateTime());
			userDetail.setUpdatedBy(userDetailDto.getUpdatedBy());
			// userDetail.setUpdatedDateTime(userDetailDto.getUpdatedDateTime());
			userDetail.setIsActive(1);

			authRepository.save(userDetail);
		} else {
			System.out.println("User already exist");
		}

		return userDetailDto;
	}

	private Role checkRoleExist() {
		System.out.println("New role added");
		Role role = new Role();
		role.setName("ROLE_MODERATOR");
		return roleRepo.save(role);
	}

	@Override
	public UserDto loginUser(UserDto userDetailDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PinDto setPin(PinDto pinDto) {

		User user = authRepository.findById((long) pinDto.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		Company company = companyRepository.findById((long) pinDto.getCompanyId())
				.orElseThrow(() -> new RuntimeException("Company not found"));

		if (user == null) {
			System.out.println("User not found");
		} else if (company == null) {
			System.out.println("Company not found");
		} else {

			List<Pin> userDetailList = pinRepository.findAllByPinCode(pinDto.getOldPinCode());

			if (CollectionUtils.isEmpty(userDetailList)) {
				Pin pinDetail = new Pin();

				pinDetail.setUser(user);
				pinDetail.setCompany(company);
				pinDetail.setPinCode(pinDto.getPinCode());
				// userDetail.setPassword(pinDto.getPassword());

				pinRepository.save(pinDetail);
			} else {
				System.out.println("User already exist");
			}
		}
		return pinDto;
	}

	@Override
	public PinDto forgotPin(PinDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PinDto changePin(PinDto pinDto) {
//		List<Pin> userDetailList = pinRepository.findAllByPinCode(pinDto.getOldPinCode());
//
//		if (CollectionUtils.isEmpty(userDetailList)) {
//			Pin userDetail = new Pin();
//
//			userDetail.setPinCode(pinDto.getPinCode());
//			userDetail.setOldPinCode(pinDto.getOldPinCode());
//
//			pinRepository.save(userDetail);
//		} else {
//			System.out.println("User already exist");
//		}

		Pin pinData = pinRepository.findByPinCode(pinDto.getOldPinCode());
		if (pinData != null) {

			pinData.setOldPinCode(pinDto.getOldPinCode());
			pinData.setPinCode(pinDto.getPinCode());

			pinRepository.save(pinData);
		} else {
			System.out.println("Pin code not found");
		}
		return pinDto;
	}

}
