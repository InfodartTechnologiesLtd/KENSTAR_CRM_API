package com.infodart.kenstar_crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.dto.ResponseDto;
import com.infodart.kenstar_crm.dto.RoleDto;
import com.infodart.kenstar_crm.dto.UserDto;
import com.infodart.kenstar_crm.entity.User;
import com.infodart.kenstar_crm.service.UserService;
import com.infodart.kenstar_crm.util.Utils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/userDetails")
@Tag(name = "User Controller", description = "API for User Management")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/getUser")
	@Operation(summary = "Get users", description = "Fetch user details via username/mobilenumber from the database")
	public ResponseEntity<ResponseDto<UserDto>> getUser(@RequestBody UserDto userDetailDto) {

		// Check if input is null or has missing required fields (you can customize
		// this)
		// Validate empty/null input fields
		if (userDetailDto == null || (Utils.isNullOrEmpty(userDetailDto.getUsername())
				&& Utils.isNullOrEmpty(userDetailDto.getMobilenumber())
				&& Utils.isNullOrEmpty(userDetailDto.getEmail()))) {

			ResponseDto<UserDto> responseDto = ResponseDto.error("400",
					"Invalid input: username, mobile number, and email are required", null);
			return ResponseEntity.badRequest().body(responseDto);
		}
		try {

			UserDto userDto = userService.getUser(userDetailDto);
			if (null == userDto || userDto.getId() == null || userDto.getIsActive() == 0) {
				ResponseDto<UserDto> responseDto = ResponseDto.error("404", "User not found", null);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
			} else {
				ResponseDto<UserDto> responseDto = ResponseDto.success("200", "User found successfully", userDto);
				return ResponseEntity.ok(responseDto);
			}
		} catch (Exception e) {
			// Log the error for debugging (optional)
			e.printStackTrace();

			ResponseDto<UserDto> responseDto = ResponseDto.error("500", "An error occurred while retrieving the user",
					null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
		}
	}

	@GetMapping(value = "/getAllUser")
	@Operation(summary = "Get all users", description = "Fetch all user details from the database")
	public ResponseEntity<ResponseDto<List<UserDto>>> getAllUsers() {
		List<UserDto> userDtoList = userService.getAllUsers();

		if (userDtoList == null || userDtoList.isEmpty()) {
			ResponseDto<List<UserDto>> responseDto = ResponseDto.error("204", "No users found", null);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDto);
		}
		try {
			ResponseDto<List<UserDto>> responseDto = ResponseDto.success("200", "User list retrieved successfully",
					userDtoList);
			return ResponseEntity.ok(responseDto);

		} catch (Exception e) {
			e.printStackTrace(); // Optional: use proper logging instead
			ResponseDto<List<UserDto>> responseDto = ResponseDto.error("500", "An error occurred while fetching users",
					null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
		}

	}

	@PostMapping(value = "/addUser")
	@Operation(summary = "Add users", description = "Add user details into the database")
	public ResponseEntity<ResponseDto<UserDto>> addUser(@RequestBody UserDto userDetailDto) {
		// Basic null check (you can expand this with validation annotations too)
		if (userDetailDto == null) {
			ResponseDto<UserDto> responseDto = ResponseDto.error("400", "User details must not be null", null);
			return ResponseEntity.badRequest().body(responseDto);
		}

		try {
			UserDto userDto = userService.addUser(userDetailDto);

			if (userDto == null || userDto.getId() == null) {
				ResponseDto<UserDto> responseDto = ResponseDto.error("500", "Failed to create user", null);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
			}

			ResponseDto<UserDto> responseDto = ResponseDto.success("201", "User created successfully", userDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

		} catch (Exception e) {
			e.printStackTrace(); // Optional: use proper logging instead
			ResponseDto<UserDto> responseDto = ResponseDto.error("500", "An error occurred while creating the user",
					null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
		}

	}

	@PutMapping("/{id}")
	@Operation(summary = "Update users", description = "Update user details into the database")
	public ResponseEntity<ResponseDto<UserDto>> updateUser(@PathVariable Long id, @RequestBody UserDto userDetailDto) {
		if (userDetailDto == null) {
			ResponseDto<UserDto> responseDto = ResponseDto.error("400", "User details must not be null", null);
			return ResponseEntity.badRequest().body(responseDto);
		}
		try {
			UserDto updatedUser = userService.updateUser(id, userDetailDto);
			// return ResponseEntity.ok(updatedUser);
			ResponseDto<UserDto> responseDto = ResponseDto.success("200", "User updated successfully", updatedUser);
			return ResponseEntity.ok(responseDto);
		} catch (Exception e) {
			e.printStackTrace(); // Optional: use proper logging instead
			ResponseDto<UserDto> responseDto = ResponseDto.error("500", "An error occurred while update the user",
					null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
		}
	}

	@PatchMapping("/{id}")
	@Operation(summary = "Partial Update users", description = "Partial update user details into the database")
	public ResponseEntity<ResponseDto<UserDto>> partiallyUpdateUser(@PathVariable Long id,
			@RequestBody UserDto userDetailDto) {

		if (userDetailDto == null) {
			ResponseDto<UserDto> responseDto = ResponseDto.error("400", "User details must not be null", null);
			return ResponseEntity.badRequest().body(responseDto);
		}
		try {
			UserDto updatedUser = userService.partiallyUpdateUser(id, userDetailDto);
			// return ResponseEntity.ok(updatedUser);
			ResponseDto<UserDto> responseDto = ResponseDto.success("200", "User updated successfully", updatedUser);
			return ResponseEntity.ok(responseDto);
		} catch (Exception e) {
			e.printStackTrace(); // Optional: use proper logging instead
			ResponseDto<UserDto> responseDto = ResponseDto.error("500", "An error occurred while update the user",
					null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete users", description = "Delete user details into the database")
	public ResponseEntity<ResponseDto<UserDto>> deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);
			UserDto user = new UserDto();
			ResponseDto<UserDto> responseDto = ResponseDto.success("200", "User deleted successfully", user);
			return ResponseEntity.ok(responseDto);
		} catch (Exception e) {
			e.printStackTrace(); // Optional: use proper logging instead
			ResponseDto<UserDto> responseDto = ResponseDto.error("500", "An error occurred while delete the user",
					null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
		}
	}

	@PatchMapping("/{id}/deactivate")
	@Operation(summary = "Deactivate users", description = "Deactivate user details into the database")
	public ResponseEntity<ResponseDto<UserDto>> deactivateUser(@PathVariable Long id) {
		try {
			UserDto user = userService.deactivateUser(id);
			// return ResponseEntity.ok(user);
			ResponseDto<UserDto> responseDto = ResponseDto.success("200", "User de-activated successfully", user);
			return ResponseEntity.ok(responseDto);
		} catch (Exception e) {
			e.printStackTrace(); // Optional: use proper logging instead
			ResponseDto<UserDto> responseDto = ResponseDto.error("500", "An error occurred while de-activate the user",
					null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
		}
	}

	@PatchMapping("/{id}/activate")
	@Operation(summary = "Activate users", description = "Activate user details into the database")
	public ResponseEntity<ResponseDto<UserDto>> activateUser(@PathVariable Long id) {
		try {
			UserDto user = userService.activateUser(id);
			// return ResponseEntity.ok(user);
			ResponseDto<UserDto> responseDto = ResponseDto.success("200", "User activated successfully", user);
			return ResponseEntity.ok(responseDto);
		} catch (Exception e) {
			e.printStackTrace(); // Optional: use proper logging instead
			ResponseDto<UserDto> responseDto = ResponseDto.error("500", "An error occurred while activate the user",
					null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
		}
	}

	@GetMapping(value = "/getRoles")
	@Operation(summary = "Get all roles", description = "Fetch all roles details from the database")
	public ResponseEntity<ResponseDto<List<RoleDto>>> getAllRoles() {
		List<RoleDto> roles = userService.getAllRoles();

		if (roles == null || roles.isEmpty()) {
			ResponseDto<List<RoleDto>> responseDto = ResponseDto.error("204", "No roles found", null);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDto);
		}
		try {
			ResponseDto<List<RoleDto>> responseDto = ResponseDto.success("200", "Roles fetched successfully", roles);
			return ResponseEntity.ok(responseDto);
		} catch (Exception e) {
			e.printStackTrace(); // Optional: use proper logging instead
			ResponseDto<List<RoleDto>> responseDto = ResponseDto.error("500",
					"An error occurred while fetching the roles", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
		}
	}

}
