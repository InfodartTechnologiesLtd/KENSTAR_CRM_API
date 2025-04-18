package com.infodart.kenstar_crm.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.infodart.kenstar_crm.dto.UserDto;
import com.infodart.kenstar_crm.entity.User;

public class UserMapper {

	
	public static UserDto toDto(User user) {
        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setMobilenumber(user.getMobilenumber());
        //dto.setRole(user.getRoles().getName());
        if (user.getRoles() != null) {
			dto.setRole(user.getRoles().getName());
		}
        dto.setIsActive(user.getIsActive());
        return dto;
    }

    public static List<UserDto> toDtoList(List<User> users) {
        return users.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }
}
