package com.infodart.kenstar_crm.service;

import java.time.LocalDate;
import java.util.List;

import com.infodart.kenstar_crm.dto.HolidayDto;

public interface HolidayService {
	HolidayDto createHoliday(HolidayDto dto);

	List<HolidayDto> getAllHolidays();

	boolean isHoliday(LocalDate date);
}
