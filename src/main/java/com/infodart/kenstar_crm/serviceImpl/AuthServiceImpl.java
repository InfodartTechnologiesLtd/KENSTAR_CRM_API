package com.infodart.kenstar_crm.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
import com.infodart.kenstar_crm.exceptions.ResourceNotFoundException;
import com.infodart.kenstar_crm.exceptions.UserNotFoundException;
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

	@Autowired
	private UserRepository userRepository;

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

			// Role role = roleRepo.findByName(rolesAsString);
			Optional<Role> optionalRole = roleRepo.findByName(rolesAsString);
			Role role = null;
			if (optionalRole.isEmpty()) {
				role = new Role();
				role = checkRoleExist();
			}
			// userDetail.setRoles(Set.of(role));
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

//		User user = authRepository.findById((long) pinDto.getUserId())
//				.orElseThrow(() -> new RuntimeException("User not found"));
//
//		Company company = companyRepository.findById((long) pinDto.getCompanyId())
//				.orElseThrow(() -> new RuntimeException("Company not found"));
//
//		if (user == null) {
//			System.out.println("User not found");
//		} else if (company == null) {
//			System.out.println("Company not found");
//		} else {

//			List<Pin> userDetailList = pinRepository.findAllByPinCode(pinDto.getOldPinCode());
//
//			if (CollectionUtils.isEmpty(userDetailList)) {
//				Pin pinDetail = new Pin();
//
//				pinDetail.setUser(user);
//				pinDetail.setCompany(company);
//				pinDetail.setPinCode(pinDto.getPinCode());
//				// userDetail.setPassword(pinDto.getPassword());
//
//				pinRepository.save(pinDetail);
//			} else {
//				System.out.println("User already exist");
//			}

		Optional<User> optionalUser = userRepository.findById(pinDto.getUserId());
		if (!optionalUser.isPresent()) {
			throw new IllegalStateException("User not found with ID: " + pinDto.getUserId());
		}

		// Check if the user already has a PIN
//	        if (pinRepository.existsByUserId(pinDto.getUserId())) {
//	            throw new IllegalStateException("PIN already exists for user ID: " + pinDto.getUserId());
//	        }

		// Fetch PIN detail by user ID
		Optional<Pin> optionalPin = java.util.Optional.empty();
		optionalPin = pinRepository.findByUser_Id(pinDto.getUserId());

		if (!optionalPin.isEmpty())
			throw new ResourceNotFoundException("PIN already exists for user ID: " + pinDto.getUserId());

		// Find company by ID, it returns an Optional
		Optional<Company> companyOptional = companyRepository.findById(pinDto.getCompanyId());

		// Check if the company exists
		if (!companyOptional.isPresent()) {
			// If the company doesn't exist, throw a custom exception or handle it
			// accordingly
			throw new ResourceNotFoundException("Company with ID " + pinDto.getCompanyId() + " not found");
		}

		// Get the Company entity from Optional
		Company company = companyOptional.get();

		// Hash the PIN using BCrypt
		String hashedPin = BCrypt.hashpw(pinDto.getPinCode(), BCrypt.gensalt());

		// Create and save the Pin entity
		Pin pin = new Pin();
		pin.setUser(optionalUser.get());
		pin.setPinCode(hashedPin); // Store the hashed PIN
		pin.setCompany(company);
		pinRepository.save(pin);

		// Prepare PinDto response
		PinDto savedPinDto = new PinDto();
		savedPinDto.setUserId(pinDto.getUserId());
		savedPinDto.setPinCode(hashedPin); // You may choose to send the hashed PIN, but normally, you'd omit it for
											// security reasons.

		return savedPinDto;

		// }

	}

	@Override
	public PinDto forgotPin(Long userId, PinDto pinDto) {
		// Check if user exists 
				User user = userRepository.findById(userId)
						.orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

				// Fetch PIN detail by user ID
				Pin pinDetail = pinRepository.findByUser_Id(userId)
						.orElseThrow(() -> new ResourceNotFoundException("PIN not found with given input. Create your PIN."));

				System.out.println("pinDto.getPinCode() : " + pinDetail.getPinCode()); 
				
				// Find company by ID, it returns an Optional
				Optional<Company> companyOptional = companyRepository.findById(pinDto.getCompanyId());

				// Check if the company exists
				if (!companyOptional.isPresent()) {
					// If the company doesn't exist, throw a custom exception or handle it
					// accordingly
					throw new ResourceNotFoundException("Company with ID " + pinDto.getCompanyId() + " not found");
				}

				// Get the Company entity from Optional
				Company company = companyOptional.get();

		// Hash new PIN
		String hashedPin = BCrypt.hashpw("1234", BCrypt.gensalt());

//	    user.setPin(hashedPin); // assuming you have a setPin method
//	    userRepository.save(user);

		// Create and save the Pin entity
		//Pin pin = new Pin();
		//pin.setUser(user);
		pinDetail.setPinCode(hashedPin); // Store the hashed PIN
		pinDetail.setCompany(company);
		pinRepository.save(pinDetail);

		// Prepare response
		PinDto responseDto = new PinDto();
		responseDto.setPinCode("****"); // Masked in response for security

		return responseDto;
	}

	@Override
	public PinDto changePin(Long userId, PinDto pinDto) {

		// Input validation
		if (pinDto.getOldPinCode() == null || pinDto.getOldPinCode().isBlank()) {
			throw new IllegalArgumentException("Old PIN must not be empty.");
		}

		if (pinDto.getPinCode() == null || pinDto.getPinCode().isBlank()) {
			throw new IllegalArgumentException("New PIN must not be empty.");
		}

		if (pinDto.getOldPinCode().equals(pinDto.getPinCode())) {
			throw new IllegalArgumentException("New PIN cannot be the same as the old PIN.");
		}

		// Check if user exists
//		if (!userRepository.findById(userId)) {
//			throw new ResourceNotFoundException("User not found with given input.");
//		}
		
		// Check if user exists 
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

		// Fetch PIN detail by user ID
		Pin pinDetail = pinRepository.findByUser_Id(userId)
				.orElseThrow(() -> new ResourceNotFoundException("PIN not found with given input."));

		System.out.println("pinDto.getPinCode() : " + pinDetail.getPinCode()); 
		
		// Find company by ID, it returns an Optional
		Optional<Company> companyOptional = companyRepository.findById(pinDto.getCompanyId());

		// Check if the company exists
		if (!companyOptional.isPresent()) {
			// If the company doesn't exist, throw a custom exception or handle it
			// accordingly
			throw new ResourceNotFoundException("Company with ID " + pinDto.getCompanyId() + " not found");
		}

		// Get the Company entity from Optional
		Company company = companyOptional.get();

		System.out.println("Company name : " + company.getCompanyName()); 
		
		System.out.println("pinDto.getOldPinCode() : " + pinDto.getOldPinCode()); 
		System.out.println("pinDetail.getOldPinCode() : " + pinDetail.getOldPinCode()); 
		// Match old PIN
		if (!BCrypt.checkpw(pinDto.getOldPinCode(), pinDetail.getPinCode())) {
			throw new IllegalArgumentException("Old PIN is incorrect.");
		}

		String oldPinCode = pinDetail.getPinCode();
		// Encrypt new PIN
		String newEncryptedPin = BCrypt.hashpw(pinDto.getPinCode(), BCrypt.gensalt());
		pinDetail.setPinCode(newEncryptedPin);
		pinDetail.setOldPinCode(oldPinCode);
		pinDetail.setCompany(company);
		pinRepository.save(pinDetail);

		// Return response with masked PIN
		PinDto responseDto = new PinDto();
		responseDto.setPinCode("****");
		responseDto.setUserId(userId);

		return responseDto;
	}

}
