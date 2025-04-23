package com.infodart.kenstar_crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.dto.PinDto;
import com.infodart.kenstar_crm.dto.ResponseDto;
import com.infodart.kenstar_crm.dto.UserDto;
import com.infodart.kenstar_crm.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

//	@PostMapping(value = "/registerUser")
//	public ResponseEntity<ResponseDto<UserDto>> addUser(@RequestBody UserDto userDetailDto) {
//		UserDto userdto = authService.registerUser(userDetailDto);
//		return ResponseEntity.ok(ResponseDto.success("200", "User created successfully", userdto));
//	}

//	@PostMapping(value = "/setPin")
//	public ResponseEntity<ResponseDto<PinDto>> setPin(@RequestBody PinDto userDetailDto) {
//		PinDto pinDto = authService.setPin(userDetailDto);
//		return ResponseEntity.ok(ResponseDto.success("200", "Pin created successfully", pinDto));
//	}
	
	@PostMapping("/createPin")
    @Operation(summary = "Create PIN", description = "Creates a new PIN for the user")
    public ResponseEntity<ResponseDto<PinDto>> createPin(@RequestBody PinDto request) {
        try {
            PinDto pinDto = authService.setPin(request);

            return ResponseEntity.ok(ResponseDto.success("200", "PIN created successfully", pinDto));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.error("400", e.getMessage(), null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDto.error("500", "Internal Server Error", null));
        }
    }
	

//	@PostMapping(value = "/forgetPin")
//	public ResponseEntity<ResponseDto<PinDto>> forgotPin(@RequestBody PinDto userDetailDto) {
//		PinDto pinDto = authService.forgotPin(userDetailDto);
//		return ResponseEntity.ok(ResponseDto.success("200", "Old Pin send to your registered emailId", pinDto));
//	}
	
	
	@PostMapping("/forgotPin/{userId}")
	@Operation(summary = "Forgot PIN", description = "Update PIN using Forgot PIN flow")
	public ResponseEntity<ResponseDto<PinDto>> forgotPin(
	        @PathVariable Long userId,
	        @RequestBody PinDto pinDto) {

	    PinDto updatedPinDto = authService.forgotPin(userId, pinDto);

	    ResponseDto<PinDto> response = ResponseDto.success(
	            "200", "Default PIN 1234 updated successfully. Please change it after login.", updatedPinDto);

	    return ResponseEntity.ok(response);
	}
	

//	@PostMapping(value = "/changePin")
//	public ResponseEntity<ResponseDto<PinDto>> changePin(@RequestBody PinDto userDetailDto) {
//		PinDto pinDto = authService.changePin(userDetailDto);
//		return ResponseEntity.ok(ResponseDto.success("200", "Pin Changed successfully", pinDto));
//	}
	
	
	@PutMapping("/changePin/{userId}")
	@Operation(summary = "Change PIN", description = "Change user PIN after verifying old PIN")
	public ResponseEntity<ResponseDto<PinDto>> changePin(
	        @PathVariable Long userId,
	        @RequestBody PinDto requestDto) {

	    PinDto updatedPin = authService.changePin(userId, requestDto);

	    ResponseDto<PinDto> response = ResponseDto.success(
	            "200", "PIN changed successfully", updatedPin);

	    return ResponseEntity.ok(response);
	}

}
