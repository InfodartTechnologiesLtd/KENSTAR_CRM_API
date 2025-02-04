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

@RestController
@RequestMapping("/attendanceManage")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	// 1. Mark Attendance In
	@PostMapping("/in/{employeeId}")
	public Attendance markAttendanceIn(@PathVariable Long employeeId) {
		return attendanceService.markAttendanceIn(employeeId);
	}

	// 2. Mark Attendance Out
	@PostMapping("/out/{employeeId}")
	public Attendance markAttendanceOut(@PathVariable Long employeeId) {
		return attendanceService.markAttendanceOut(employeeId);
	}

	// 3. Get Last 7 Days Attendance
	@GetMapping("/last7days/{employeeId}")
	public List<Attendance> getLast7DaysAttendance(@PathVariable Long employeeId) {
		return attendanceService.getLast7DaysAttendance(employeeId);
	}

}
