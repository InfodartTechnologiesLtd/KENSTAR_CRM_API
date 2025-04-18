package com.infodart.kenstar_crm.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infodart.kenstar_crm.dto.RoleDto;
import com.infodart.kenstar_crm.dto.UserDto;
import com.infodart.kenstar_crm.entity.ERole;
import com.infodart.kenstar_crm.entity.Role;
import com.infodart.kenstar_crm.entity.User;
import com.infodart.kenstar_crm.exceptions.ResourceNotFoundException;
import com.infodart.kenstar_crm.exceptions.UserAlreadyInactiveException;
import com.infodart.kenstar_crm.exceptions.UserNotFoundException;
import com.infodart.kenstar_crm.mapper.UserMapper;
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
			throw new IllegalArgumentException("At least one of username, email, or mobile number is required.");

		List<User> userDetailList = new ArrayList<>();

		if (!Utils.isNullOrEmpty(userDetail.getUsername())) {
			userDetailList = userDetailRepo.findAllByUsername(userDetail.getUsername());
		} else if (!Utils.isNullOrEmpty(userDetail.getEmail())) {
			userDetailList = userDetailRepo.findAllByEmail(userDetail.getEmail());
		} else if (!Utils.isNullOrEmpty(userDetail.getMobilenumber())) {
			userDetailList = userDetailRepo.findAllByMobilenumber(userDetail.getMobilenumber());
		}

		if (userDetailList.isEmpty())
			throw new ResourceNotFoundException("User not found with given input.");

		User users = userDetailList.get(0);
		if (users.getIsActive() == 0) {
			throw new IllegalArgumentException("User is inactive.");
		}

		// Pick the first ACTIVE user
		for (User user : userDetailList) {
			if (user.getIsActive() == 1) {
				UserDto userDto = UserMapper.toDto(user);
				return userDto;
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
		UserDto savedUserDto = UserMapper.toDto(savedUser);
		return savedUserDto;
	}

	private Role checkRoleExist() {
		ERole defaultRole = ERole.ROLE_MODERATOR;
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
			userDetailDtoList.add(UserMapper.toDto(user));
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

	@Override
	public UserDto updateUser(Long id, UserDto dto) {
		User user = userDetailRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

		// user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		// user.setRoles(dto.getRole().get);

		if (dto.getRole() != null && !dto.getRole().isEmpty()) {
			String rolesAsString = String.join(", ", dto.getRole());

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

		User updatedUser = userDetailRepo.save(user);
		return UserMapper.toDto(updatedUser);
	}

	@Override
	public UserDto partiallyUpdateUser(Long id, UserDto dto) {
		User user = userDetailRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

		if (dto.getEmail() != null)
			user.setEmail(dto.getEmail());
		if (dto.getRole() != null && !dto.getRole().isEmpty()) {
			String rolesAsString = String.join(", ", dto.getRole());

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

		User updatedUser = userDetailRepo.save(user);
		return UserMapper.toDto(updatedUser);
	}

	@Override
	public void deleteUser(Long id) {
		User user = userDetailRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
		userDetailRepo.delete(user);
	}

	@Override
	public UserDto deactivateUser(Long id) {
		User user = userDetailRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

		if (user.getIsActive() == 0) {
			throw new UserAlreadyInactiveException("User is already deactivated");
		}

		user.setIsActive(0);
		User updatedUser = userDetailRepo.save(user);
		return UserMapper.toDto(updatedUser);
	}

	@Override
	public UserDto activateUser(Long id) {
		User user = userDetailRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

		if (user.getIsActive() == 1) {
			throw new RuntimeException("User is already active");
		}

		user.setIsActive(1);
		User updatedUser = userDetailRepo.save(user);
		return UserMapper.toDto(updatedUser);
	}


}
