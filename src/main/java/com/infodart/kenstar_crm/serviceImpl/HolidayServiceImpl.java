package com.infodart.kenstar_crm.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infodart.kenstar_crm.dto.HolidayDto;
import com.infodart.kenstar_crm.entity.Holiday;
import com.infodart.kenstar_crm.entity.Role;
import com.infodart.kenstar_crm.mapper.HolidayMapper;
import com.infodart.kenstar_crm.repository.HolidayRepository;
import com.infodart.kenstar_crm.service.HolidayService;

@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	private HolidayRepository holidayRepository;

	@Override
	public HolidayDto createHoliday(HolidayDto dto) {
		
		Optional<Holiday> optionalHoliday = holidayRepository.findByDate(dto.getDate());
		if (!optionalHoliday.isEmpty()) {
			throw new RuntimeException("Holiday already exists for this date");
		}
//		if (holidayRepository.existsByDate(dto.getDate())) {
//			throw new RuntimeException("Holiday already exists for this date");
//		}
		Holiday saved = holidayRepository.save(HolidayMapper.toEntity(dto));
		return HolidayMapper.toDto(saved);
	}

	@Override
	public List<HolidayDto> getAllHolidays() {
		return holidayRepository.findAll().stream().map(HolidayMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public boolean isHoliday(LocalDate date) {
		
		Optional<Holiday> optionalHoliday = holidayRepository.findByDate(date);
		if (optionalHoliday.isEmpty()) {
			return false;
		}
		
		return  true;
	}
}
