package com.infodart.kenstar_crm.service;

import java.util.List;

import com.infodart.kenstar_crm.dto.CompanyDto;
import com.infodart.kenstar_crm.dto.PinDto;
import com.infodart.kenstar_crm.dto.UserDto;

public interface AuthService {

	public UserDto registerUser(UserDto userDto);
	
	public UserDto loginUser(UserDto userDto);
	
	 
	
	public PinDto setPin(PinDto userDto);
	public PinDto forgotPin(PinDto userDto);
	
	public PinDto changePin(PinDto userDto);
	
}
