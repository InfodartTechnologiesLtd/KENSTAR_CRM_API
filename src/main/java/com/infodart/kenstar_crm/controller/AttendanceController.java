package com.infodart.kenstar_crm.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.infodart.kenstar_crm.entity.Attendance;
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
    public ResponseEntity<String> markAttendance(@PathVariable Long userId, @RequestBody AttendanceDto attendaceDto) {
        attendanceService.markAttendance(userId, attendaceDto);
        return ResponseEntity.ok("Attendance marked successfully!");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AttendanceDto>> getAttendanceByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(attendanceService.getAllAttendanceByUser(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttendanceDto>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

    @GetMapping("/user/{userId}/date")
    public ResponseEntity<AttendanceDto> getAttendanceByDate(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(attendanceService.getAttendanceByDate(userId, date));
    }

    @GetMapping("/summary/{userId}")
    public ResponseEntity<AttendanceSummaryDto> getSummary(
            @PathVariable Long userId,
            @RequestParam int month,
            @RequestParam int year) {
        return ResponseEntity.ok(attendanceService.getSummary(userId, month, year));
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> approveAttendance(@PathVariable Long id) {
        attendanceService.approveAttendance(id);
        return ResponseEntity.ok("Attendance approved");
    }

    @PutMapping("/reject/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> rejectAttendance(@PathVariable Long id) {
        attendanceService.rejectAttendance(id);
        return ResponseEntity.ok("Attendance rejected");
    }

    @GetMapping("/pending-approvals")
    public ResponseEntity<List<AttendanceDto>> getPendingApprovals() {
        return ResponseEntity.ok(attendanceService.getPendingForApproval());
    }
}
