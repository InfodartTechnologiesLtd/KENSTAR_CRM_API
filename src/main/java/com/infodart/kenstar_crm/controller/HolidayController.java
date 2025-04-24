package com.infodart.kenstar_crm.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.dto.HolidayDto;
import com.infodart.kenstar_crm.entity.Holiday;
import com.infodart.kenstar_crm.mapper.HolidayMapper;
import com.infodart.kenstar_crm.repository.HolidayRepository;
import com.infodart.kenstar_crm.service.HolidayService;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

	@Autowired
	private HolidayService holidayService;

	@PostMapping
	public ResponseEntity<HolidayDto> create(@RequestBody HolidayDto dto) {
		return ResponseEntity.ok(holidayService.createHoliday(dto));
	}

	@GetMapping
	public ResponseEntity<List<HolidayDto>> getAll() {
		return ResponseEntity.ok(holidayService.getAllHolidays());
	}

}
