package com.infodart.kenstar_crm.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.infodart.kenstar_crm.dto.UserDto;
import com.infodart.kenstar_crm.entity.ERole;
import com.infodart.kenstar_crm.entity.Role;
import com.infodart.kenstar_crm.entity.User;
import com.infodart.kenstar_crm.dto.RoleDto;
import com.infodart.kenstar_crm.repository.RoleRepo;
import com.infodart.kenstar_crm.repository.UserRepository;
import com.infodart.kenstar_crm.service.UserService;
import com.infodart.kenstar_crm.util.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userDetailRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto getUser(UserDto userDetail) {

		if (userDetail == null)
			return null;

		List<User> userDetailList = new ArrayList<>();

		if (!Utils.isNullOrEmpty(userDetail.getUsername())) {
			userDetailList = userDetailRepo.findAllByUsername(userDetail.getUsername());
		} else if (!Utils.isNullOrEmpty(userDetail.getEmail())) {
			userDetailList = userDetailRepo.findAllByEmail(userDetail.getEmail());
		} else if (!Utils.isNullOrEmpty(userDetail.getMobilenumber())) {
			userDetailList = userDetailRepo.findAllByMobilenumber(userDetail.getMobilenumber());
		}

		if (userDetailList.isEmpty())
			return null;

		// Pick the first ACTIVE user
		for (User user : userDetailList) {
			if (user.getIsActive() == 1) {
				UserDto dto = new UserDto();
				dto.setId(user.getId());
				dto.setEmail(user.getEmail());
				dto.setUsername(user.getUsername());
				dto.setMobilenumber(user.getMobilenumber());
				dto.setIsActive(user.getIsActive());
				dto.setCreatedBy(user.getCreatedBy());
				 dto.setRole(user.getRoles().getName()); // Uncomment if needed
				return dto;
			}
		}

		// No active user found
		return null;
	}

	@Override
	public UserDto addUser(UserDto userDetailDto) {

		// 1. Validate input
		if (userDetailDto == null) {
			throw new IllegalArgumentException("User details must not be null");
		}

		if (Utils.isNullOrEmpty(userDetailDto.getUsername())) {
			throw new IllegalArgumentException("Username is required");
		}
		if (Utils.isNullOrEmpty(userDetailDto.getEmail())) {
			throw new IllegalArgumentException("Email is required");
		}
		if (Utils.isNullOrEmpty(userDetailDto.getMobilenumber())) {
			throw new IllegalArgumentException("Mobile number is required");
		}

		try {
			ERole.valueOf(userDetailDto.getRole()); // throws IllegalArgumentException if invalid
		} catch (IllegalArgumentException ex) {
			throw new RuntimeException("Invalid role provided");
		}

		// 2. Check for existing user (username/email/mobile)
		if (!Utils.isNullOrEmpty(userDetailDto.getUsername())
				&& userDetailRepo.existsByUsername(userDetailDto.getUsername())) {
			throw new RuntimeException("Username already exists");
		}

		if (!Utils.isNullOrEmpty(userDetailDto.getEmail()) && userDetailRepo.existsByEmail(userDetailDto.getEmail())) {
			throw new RuntimeException("Email already exists");
		}

		if (!Utils.isNullOrEmpty(userDetailDto.getMobilenumber())
				&& userDetailRepo.existsByMobilenumber(userDetailDto.getMobilenumber())) {
			throw new RuntimeException("Mobile number already exists");
		}

		// 3. Map to entity
		User user = new User();
		user.setUsername(userDetailDto.getUsername());
		user.setEmail(userDetailDto.getEmail());
		user.setMobilenumber(userDetailDto.getMobilenumber());

		// Optional: encode password (recommended)
		// user.setPassword(passwordEncoder.encode(userDetailDto.getPassword()));
		user.setPassword(userDetailDto.getPassword());

		user.setCreatedBy(userDetailDto.getCreatedBy());
		user.setUpdatedBy(userDetailDto.getUpdatedBy());
		user.setIsActive(1);

		// 4. Handle roles
		if (userDetailDto.getRole() != null && !userDetailDto.getRole().isEmpty()) {
			String rolesAsString = String.join(", ", userDetailDto.getRole());

			// Role role = roleRepo.findByName(userDetailDto.getRole());
			Optional<Role> optionalRole = roleRepo.findByName(rolesAsString);
			Role role = null;
			if (optionalRole.isEmpty()) {
				role = checkRoleExist(); // fallback to default enum role
			} else {
				role = optionalRole.get();
			}
			user.setRoles(role);

		}

		// 5. Save user
		User savedUser = userDetailRepo.save(user);

		// 6. Map back to DTO
		UserDto savedDto = new UserDto();
		savedDto.setId(savedUser.getId());
		savedDto.setUsername(savedUser.getUsername());
		savedDto.setEmail(savedUser.getEmail());
		savedDto.setMobilenumber(savedUser.getMobilenumber());
		savedDto.setIsActive(savedUser.getIsActive());
		savedDto.setCreatedBy(savedUser.getCreatedBy());
		savedDto.setUpdatedBy(savedUser.getUpdatedBy());
		// Add roles if needed

		if (savedUser.getRoles() != null) {
			savedDto.setRole(savedUser.getRoles().getName());
		}
		return savedDto;
	}

	private Role checkRoleExist() {
		ERole defaultRole = ERole.ROLE_MODERATOR;

//	    Role role = roleRepo.findByName(defaultRole.name());
//		if (role != null) {
//	        return role;
//	    }
		Optional<Role> optionalRole = roleRepo.findByName(defaultRole.name());
		Role role = null;
		if (optionalRole.isEmpty()) {

			role = new Role();
			role.setName(defaultRole.name());
			System.out.println("New role created: " + defaultRole.name());
			return roleRepo.save(role);
		} else {
			role = optionalRole.get();
			return role;
		}

	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = (List<User>) userDetailRepo.findAll();
	    List<UserDto> userDetailDtoList = new ArrayList<>();

	    for (User user : users) {
	        UserDto dto = new UserDto();
	        dto.setId(user.getId());
	        dto.setEmail(user.getEmail());
	        dto.setUsername(user.getUsername());
	        dto.setMobilenumber(user.getMobilenumber());
	        // Set single role as string
	        if (user.getRoles() != null) {
	            dto.setRole(user.getRoles().getName());
	        }
	        userDetailDtoList.add(dto);
	    }

	    return userDetailDtoList;
	}

	@Override
	public List<RoleDto> getAllRoles() {
		List<Role> roles = (List<Role>) roleRepo.findAll();

		List<RoleDto> roleDtoList = new ArrayList<>();
		for (Role role : roles) {
			RoleDto roleDto = new RoleDto();
			roleDto.setId(role.getId());
			roleDto.setName(role.getName());

			roleDtoList.add(roleDto);
		}

		return roleDtoList;
	}

	/*
	 * @Override public UserDto getUser(UserDto userDetailDto) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public UserDto addUser(UserDto userDetailDto) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public List<UserDto> getAllUsers() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public List<RoleDto> getAllRoles() { // TODO Auto-generated method
	 * stub return null; }
	 */

}
