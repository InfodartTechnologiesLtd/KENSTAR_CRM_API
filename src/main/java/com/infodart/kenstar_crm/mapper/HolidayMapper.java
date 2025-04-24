package com.infodart.kenstar_crm.mapper;

import com.infodart.kenstar_crm.dto.HolidayDto;
import com.infodart.kenstar_crm.entity.Holiday;

public class HolidayMapper {
	public static HolidayDto toDto(Holiday holiday) {
        return new HolidayDto(
            holiday.getId(),
            holiday.getName(),
            holiday.getDate(),
            holiday.getDescription(),
            holiday.getIsNationalHoliday()
        );
    }

    public static Holiday toEntity(HolidayDto dto) {
        Holiday holiday = new Holiday();
        holiday.setId(dto.getId());
        holiday.setName(dto.getName());
        holiday.setDate(dto.getDate());
        holiday.setDescription(dto.getDescription());
        holiday.setIsNationalHoliday(dto.getIsNationalHoliday());
        return holiday;
    }
}
