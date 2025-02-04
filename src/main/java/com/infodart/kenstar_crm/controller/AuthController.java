package com.infodart.kenstar_crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.dto.PinDto;
import com.infodart.kenstar_crm.dto.ResponseDto;
import com.infodart.kenstar_crm.dto.UserDto;
import com.infodart.kenstar_crm.service.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping(value = "/registerUser")
	public ResponseEntity<ResponseDto<UserDto>> addUser(@RequestBody UserDto userDetailDto) {
		UserDto userdto = authService.registerUser(userDetailDto);
		return ResponseEntity.ok(ResponseDto.success("200", "User created successfully", userdto));
	}

	@PostMapping(value = "/setPin")
	public ResponseEntity<ResponseDto<PinDto>> setPin(@RequestBody PinDto userDetailDto) {
		PinDto pinDto = authService.setPin(userDetailDto);
		return ResponseEntity.ok(ResponseDto.success("200", "Pin created successfully", pinDto));
	}

	@PostMapping(value = "/forgetPin")
	public ResponseEntity<ResponseDto<PinDto>> forgotPin(@RequestBody PinDto userDetailDto) {
		PinDto pinDto = authService.forgotPin(userDetailDto);
		return ResponseEntity.ok(ResponseDto.success("200", "Old Pin send to your registered emailId", pinDto));
	}

	@PostMapping(value = "/changePin")
	public ResponseEntity<ResponseDto<PinDto>> changePin(@RequestBody PinDto userDetailDto) {
		PinDto pinDto = authService.changePin(userDetailDto);
		return ResponseEntity.ok(ResponseDto.success("200", "Pin Changed successfully", pinDto));
	}

}
