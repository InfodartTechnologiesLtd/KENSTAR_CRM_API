package com.infodart.kenstar_crm.service;

import java.util.List;

import com.infodart.kenstar_crm.dto.RoleDto;
import com.infodart.kenstar_crm.dto.UserDto;
import com.infodart.kenstar_crm.entity.User;
 

public interface UserService {

	public UserDto getUser(UserDto userDetailDto);

	// public List<UserDetailDto> getAllUser( );

	public UserDto addUser(UserDto userDetailDto);

	public List<UserDto> getAllUsers();

	public List<RoleDto> getAllRoles();

	public UserDto updateUser(Long id, UserDto dto);
	
	public UserDto partiallyUpdateUser(Long id, UserDto dto);
	
	public void deleteUser(Long id);
	
	public UserDto deactivateUser(Long id);
	public UserDto activateUser(Long id);
}
