package com.infodart.kenstar_crm.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.dto.AttendanceDto;
import com.infodart.kenstar_crm.dto.AttendanceSummaryDto;
import com.infodart.kenstar_crm.dto.ResponseDto;
import com.infodart.kenstar_crm.service.AttendanceService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/attendanceManage")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

//	// 1. Mark Attendance In
//	@PostMapping("/in/{employeeId}")
//    @Operation(summary = "Mark AttendanceIn", description = "Mark AttendanceIn into the database")
//	public Attendance markAttendanceIn(@PathVariable Long employeeId) {
//		return attendanceService.markAttendanceIn(employeeId);
//	}
//
//	// 2. Mark Attendance Out
//	@PostMapping("/out/{employeeId}")
//	@Operation(summary = "Mark Attendance Out", description = "Mark Attendance Out into the database")
//	public Attendance markAttendanceOut(@PathVariable Long employeeId) {
//		return attendanceService.markAttendanceOut(employeeId);
//	}
//
//	// 3. Get Last 7 Days Attendance
//	@GetMapping("/last7days/{employeeId}")
//	@Operation(summary = "Get Attendance", description = "Get Attendance into the database")
//	public List<Attendance> getLast7DaysAttendance(@PathVariable Long employeeId) {
//		return attendanceService.getLast7DaysAttendance(employeeId);
//	}

	@PostMapping("/mark/{userId}")
	public ResponseEntity<ResponseDto<AttendanceDto>> markAttendance(@PathVariable Long userId,
			@RequestBody AttendanceDto attendaceDto) {
		AttendanceDto attendanceDto = attendanceService.markAttendance(userId, attendaceDto);
		ResponseDto<AttendanceDto> responseDto = ResponseDto.success("200", "Attendance marked successfully!",
				attendanceDto);
		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<ResponseDto<List<AttendanceDto>>> getAttendanceByUser(@PathVariable Long userId) {
		List<AttendanceDto> attendanceDtoList = attendanceService.getAllAttendanceByUser(userId);
		if (attendanceDtoList.isEmpty()) {
			ResponseDto<List<AttendanceDto>> responseDto = ResponseDto.error("204", "No Attendance found", null);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDto);
		}
		ResponseDto<List<AttendanceDto>> responseDto = ResponseDto.success("200", "Attendance retrieved successfully",
				attendanceDtoList);
		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/all")
	public ResponseEntity<ResponseDto<List<AttendanceDto>>> getAllAttendance() {
		List<AttendanceDto> attendanceDtoList = attendanceService.getAllAttendance();
		if (attendanceDtoList.isEmpty()) {
			ResponseDto<List<AttendanceDto>> responseDto = ResponseDto.error("204", "No Attendance found", null);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDto);
		}
		ResponseDto<List<AttendanceDto>> responseDto = ResponseDto.success("200", "Attendance retrieved successfully",
				attendanceDtoList);
		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/user/{userId}/date")
	public ResponseEntity<ResponseDto<AttendanceDto>> getAttendanceByDate(@PathVariable Long userId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		AttendanceDto attendanceDto = attendanceService.getAttendanceByDate(userId, date);
		ResponseDto<AttendanceDto> responseDto = ResponseDto.success("200", "Attendance fetch successfully!",
				attendanceDto);
		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/summary/{userId}")
	public ResponseEntity<ResponseDto<AttendanceSummaryDto>> getSummary(@PathVariable Long userId,
			@RequestParam int month, @RequestParam int year) {
		AttendanceSummaryDto attendanceSummaryDto = attendanceService.getSummary(userId, month, year);
		ResponseDto<AttendanceSummaryDto> responseDto = ResponseDto.success("200",
				"Attendance summary fetch successfully!", attendanceSummaryDto);
		return ResponseEntity.ok(responseDto);
	}

	@PutMapping("/approve/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<AttendanceDto>> approveAttendance(@PathVariable Long id) {
		AttendanceDto attendanceDto = attendanceService.approveAttendance(id);
		ResponseDto<AttendanceDto> responseDto = ResponseDto.success("200", "Attendance approved!", attendanceDto);
		return ResponseEntity.ok(responseDto);
	}

	@PutMapping("/reject/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<AttendanceDto>> rejectAttendance(@PathVariable Long id) {
		AttendanceDto attendanceDto = attendanceService.rejectAttendance(id);
		ResponseDto<AttendanceDto> responseDto = ResponseDto.success("200", "Attendance rejected!", attendanceDto);
		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/pending-approvals")
	public ResponseEntity<ResponseDto<List<AttendanceDto>>> getPendingApprovals() {
		List<AttendanceDto> attendanceDtoList = attendanceService.getPendingForApproval();
		if (attendanceDtoList.isEmpty()) {
			ResponseDto<List<AttendanceDto>> responseDto = ResponseDto.error("204", "No Attendance found", null);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDto);
		}
		ResponseDto<List<AttendanceDto>> responseDto = ResponseDto.success("200", "Attendance retrieved successfully",
				attendanceDtoList);
		return ResponseEntity.ok(responseDto);
	}
	
	//	Get Last 7 Days Attendance
	@GetMapping("/last7days/{userId}")
	@Operation(summary = "Get Attendance", description = "Get last 7 days Attendance from database")
	public ResponseEntity<ResponseDto<List<AttendanceDto>>> getLast7DaysAttendance(@PathVariable Long userId) {
		List<AttendanceDto> attendanceDtoList = attendanceService.getLast7DaysAttendance(userId);
		if (attendanceDtoList.isEmpty()) {
			ResponseDto<List<AttendanceDto>> responseDto = ResponseDto.error("204", "No Attendance found", null);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDto);
		}
		ResponseDto<List<AttendanceDto>> responseDto = ResponseDto.success("200", "Attendance retrieved successfully",
				attendanceDtoList);
		return ResponseEntity.ok(responseDto);
	}
}
