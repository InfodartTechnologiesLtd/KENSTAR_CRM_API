package com.infodart.kenstar_crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.dto.ResponseDto;
import com.infodart.kenstar_crm.dto.RoleDto;
import com.infodart.kenstar_crm.dto.UserDto;
import com.infodart.kenstar_crm.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/userDetails")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/getUser")
	public ResponseEntity<ResponseDto<UserDto>> getUser(@RequestBody UserDto userDetailDto) {
		UserDto userDto = userService.getUser(userDetailDto);
		if (userDto == null) {
	        ResponseDto<UserDto> responseDto = ResponseDto.error("300", "User not found", null);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	    } else {
	        ResponseDto<UserDto> responseDto = ResponseDto.success("200", "User found successfully", userDto);
	        return ResponseEntity.ok(responseDto);
	    }
	}

	@GetMapping(value = "/getAllUser")
	public ResponseEntity<ResponseDto<List<UserDto>>> getAllUsers() {
		List<UserDto> userDtoList = userService.getAllUsers();
		return ResponseEntity.ok(ResponseDto.success("200", "User List found successfully", userDtoList));
	}

	@PostMapping(value = "/addUser")
	public ResponseEntity<ResponseDto<UserDto>> addUser(@RequestBody UserDto userDetailDto) {
		UserDto userDto = userService.addUser(userDetailDto);
		return ResponseEntity.ok(ResponseDto.success("200", "User created successfully", userDto));

	}

	@GetMapping(value = "/getRoles")
	public List<RoleDto> getAllRoles() {
		return userService.getAllRoles();
	}

}
