package com.infodart.kenstar_crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.dto.LeaveDto;
import com.infodart.kenstar_crm.dto.LeaveRequestDto;
import com.infodart.kenstar_crm.dto.ResponseDto;
import com.infodart.kenstar_crm.service.LeaveService;

@RestController
@RequestMapping("/leaveManage")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;

	// Apply for leave
	@PostMapping("/apply")
	public ResponseEntity<ResponseDto<LeaveDto>> applyLeave(@RequestParam Long userId,
			@RequestBody LeaveRequestDto dto) {
		LeaveDto leaveDto = leaveService.applyLeave(userId, dto);
		ResponseDto<LeaveDto> responseDto = ResponseDto.success("200", "Leave request submitted successfully.",
				leaveDto);
		return ResponseEntity.ok(responseDto);
	}

	// Approve leave
	@PutMapping("/approve/{leaveId}")
	public ResponseEntity<ResponseDto<LeaveDto>> approveLeave(@PathVariable Long leaveId,
			@RequestParam Long managerId) {
		LeaveDto leaveDto = leaveService.approveLeave(leaveId, managerId);
		ResponseDto<LeaveDto> responseDto = ResponseDto.success("200", "Leave approved successfully.", leaveDto);
		return ResponseEntity.ok(responseDto);
	}

	// Reject leave
	@PutMapping("/reject/{leaveId}")
	public ResponseEntity<ResponseDto<LeaveDto>> rejectLeave(@PathVariable Long leaveId, @RequestParam Long managerId,
			@RequestParam String reason) {
		LeaveDto leaveDto = leaveService.rejectLeave(leaveId, managerId, reason);
		ResponseDto<LeaveDto> responseDto = ResponseDto.success("200", "Leave rejected successfully.", leaveDto);
		return ResponseEntity.ok(responseDto);
	}

	// Get leaves for a user
	@GetMapping("/user/{userId}")
	public ResponseEntity<ResponseDto<List<LeaveDto>>> getLeavesByUser(@PathVariable Long userId) {
		List<LeaveDto> leaveDtList = leaveService.getLeavesByUser(userId);

		if (leaveDtList.isEmpty()) {
			ResponseDto<List<LeaveDto>> responseDto = ResponseDto.error("204", "No Leave found", null);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDto);
		}
		ResponseDto<List<LeaveDto>> responseDto = ResponseDto.success("200", "Leave retrieved successfully",
				leaveDtList);

		return ResponseEntity.ok(responseDto);
	}

	// Get all pending leaves (admin/moderator only)
	@GetMapping("/pending")
	public ResponseEntity<ResponseDto<List<LeaveDto>>> getAllPendingLeaves() {
		List<LeaveDto> pendingLeavesList = leaveService.getAllPendingLeaves();
		if (pendingLeavesList.isEmpty()) {
			ResponseDto<List<LeaveDto>> responseDto = ResponseDto.error("204", "No pending leave found", null);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDto);
		}
		ResponseDto<List<LeaveDto>> responseDto = ResponseDto.success("200", "Pending Leave retrieved successfully",
				pendingLeavesList);
		return ResponseEntity.ok(responseDto);
	}

}
