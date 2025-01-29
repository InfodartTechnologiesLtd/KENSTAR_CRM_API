package com.infodart.kenstar_crm.service;

import java.util.List;

import com.infodart.kenstar_crm.dto.RoleDto;
import com.infodart.kenstar_crm.dto.UserDto;

public interface UserService {

	public UserDto getUser(UserDto userDetailDto);

	// public List<UserDetailDto> getAllUser( );

	public UserDto addUser(UserDto userDetailDto);

	public List<UserDto> getAllUsers();

	public List<RoleDto> getAllRoles();

}
