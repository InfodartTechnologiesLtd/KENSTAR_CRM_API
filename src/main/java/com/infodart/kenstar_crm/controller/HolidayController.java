package com.infodart.kenstar_crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.dto.HolidayDto;
import com.infodart.kenstar_crm.dto.ResponseDto;
import com.infodart.kenstar_crm.service.HolidayService;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

	@Autowired
	private HolidayService holidayService;

	@PostMapping
	public ResponseEntity<ResponseDto<HolidayDto>> create(@RequestBody HolidayDto dto) {
		HolidayDto holidayDto = holidayService.createHoliday(dto);
		ResponseDto<HolidayDto> responseDto = ResponseDto.success("200", "Holiday marked successfully!", holidayDto);
		return ResponseEntity.ok(responseDto);
	}

	@GetMapping
	public ResponseEntity<ResponseDto<List<HolidayDto>>> getAll() {
		List<HolidayDto> holidayDtoList = holidayService.getAllHolidays();
		if (holidayDtoList.isEmpty()) {
			ResponseDto<List<HolidayDto>> responseDto = ResponseDto.error("204", "No Holiday found", null);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDto);
		}
		ResponseDto<List<HolidayDto>> responseDto = ResponseDto.success("200", "Holiday retrieved successfully",
				holidayDtoList);
		return ResponseEntity.ok(responseDto);
	}

}
