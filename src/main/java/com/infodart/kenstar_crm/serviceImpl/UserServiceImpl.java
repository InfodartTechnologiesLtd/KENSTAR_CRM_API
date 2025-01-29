package com.infodart.kenstar_crm.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.infodart.kenstar_crm.dto.UserDto;
import com.infodart.kenstar_crm.entity.Role;
import com.infodart.kenstar_crm.entity.User;
import com.infodart.kenstar_crm.dto.RoleDto;
import com.infodart.kenstar_crm.repository.RoleRepo;
import com.infodart.kenstar_crm.repository.UserRepository;
import com.infodart.kenstar_crm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userDetailRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto getUser(UserDto userDetail) {

		List<User> userDetailList = new ArrayList<>();
		if (!userDetail.getUsername().isBlank()) {
			userDetailList = userDetailRepo.findAllByUsername(userDetail.getUsername());
		} else if (!userDetail.getEmail().isBlank()) {
			userDetailList = userDetailRepo.findAllByEmail(userDetail.getEmail());
		} else if (!userDetail.getMobilenumber().isBlank()) {
			userDetailList = userDetailRepo.findAllByMobilenumber(userDetail.getMobilenumber());
		}

		UserDto userDetailDto = null;
		if (userDetailList.size() > 0) {
			userDetailDto = new UserDto();
			for (User userDetail2 : userDetailList) {

				userDetailDto.setId(userDetail2.getId());
				userDetailDto.setEmail(userDetail2.getEmail());
				userDetailDto.setUsername(userDetail2.getUsername());
				userDetailDto.setMobilenumber(userDetail2.getMobilenumber());
			}
			System.out.println(" getUser called 55 " + userDetailDto.toString());
		}

		return userDetailDto;
	}

	@Override
	public UserDto addUser(UserDto userDetailDto) {

		List<User> userDetailList = userDetailRepo.findAllByUsername(userDetailDto.getUsername());

		/*
		 * if (userDetailRepo.existsByUsername(userDetailDto.getUsername())) { return
		 * ResponseEntity.badRequest().body(new
		 * MessageResponse("Error: Username is already taken!")); }
		 * 
		 * if (userDetailRepo.existsByEmail(userDetailDto.getEmail())) { return
		 * ResponseEntity.badRequest().body(new
		 * MessageResponse("Error: Email is already in use!")); }
		 */

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

			Role role = roleRepo.findByName(rolesAsString);
			if (role == null) {
				role = checkRoleExist();
			}
			userDetail.setRoles(Set.of(role));

			userDetail.setCreatedBy(userDetailDto.getCreatedBy());
			// userDetail.setCreatedDateTime(userDetailDto.getCreatedDateTime());
			userDetail.setUpdatedBy(userDetailDto.getUpdatedBy());
			// userDetail.setUpdatedDateTime(userDetailDto.getUpdatedDateTime());
			userDetail.setIsActive(1);

			userDetailRepo.save(userDetail);
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
	public List<UserDto> getAllUsers() {
		// Iterable<UserDetail> userDetailList = userDetailRepo.findAll();

		List<User> users = (List<User>) userDetailRepo.findAll();

		List<UserDto> UserDetailDtoList = new ArrayList<>();
		for (User userDetail2 : users) {
			UserDto userDetailDto = new UserDto();
			userDetailDto.setId(userDetail2.getId());
			userDetailDto.setEmail(userDetail2.getEmail());
			userDetailDto.setUsername(userDetail2.getUsername());
			userDetailDto.setMobilenumber(userDetail2.getMobilenumber());

			UserDetailDtoList.add(userDetailDto);
		}

		return UserDetailDtoList;

		// return null;
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
