package com.infodart.kenstar_crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.entity.Attendance;
import com.infodart.kenstar_crm.service.AttendanceService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/attendanceManage")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	// 1. Mark Attendance In
	@PostMapping("/in/{employeeId}")
    @Operation(summary = "Mark AttendanceIn", description = "Mark AttendanceIn into the database")
	public Attendance markAttendanceIn(@PathVariable Long employeeId) {
		return attendanceService.markAttendanceIn(employeeId);
	}

	// 2. Mark Attendance Out
	@PostMapping("/out/{employeeId}")
	@Operation(summary = "Mark Attendance Out", description = "Mark Attendance Out into the database")
	public Attendance markAttendanceOut(@PathVariable Long employeeId) {
		return attendanceService.markAttendanceOut(employeeId);
	}

	// 3. Get Last 7 Days Attendance
	@GetMapping("/last7days/{employeeId}")
	@Operation(summary = "Get Attendance", description = "Get Attendance into the database")
	public List<Attendance> getLast7DaysAttendance(@PathVariable Long employeeId) {
		return attendanceService.getLast7DaysAttendance(employeeId);
	}

}
