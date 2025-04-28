package com.infodart.kenstar_crm.service;

import com.infodart.kenstar_crm.dto.PinDto;
import com.infodart.kenstar_crm.dto.UserDto;

public interface AuthService {

	public UserDto registerUser(UserDto userDto);

	public UserDto loginUser(UserDto userDto);

	public PinDto setPin(PinDto userDto);

	public PinDto forgotPin(Long userId, PinDto userDto);

	public PinDto changePin(Long userId, PinDto userDto);

}
