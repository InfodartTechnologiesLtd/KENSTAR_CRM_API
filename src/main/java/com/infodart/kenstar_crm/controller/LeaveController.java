package com.infodart.kenstar_crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.infodart.kenstar_crm.entity.Leave;
import com.infodart.kenstar_crm.enums.LeaveStatus;
import com.infodart.kenstar_crm.service.LeaveService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/leaveManage")
public class LeaveController {

	
	@Autowired
    private LeaveService leaveService;

    // 1. Apply for Leave
//    @PostMapping("/apply/{employeeId}")
//    @Operation(summary = "Apply Leave", description = "Apply Leave into the database")
//    public Leave applyForLeave(@PathVariable Long employeeId, @RequestBody Leave leaveRequest) {
//        return leaveService.applyForLeave(employeeId, leaveRequest);
//    }
//
//    // 2. Get Employee Leave History
//    @GetMapping("/history/{employeeId}")
//    @Operation(summary = "Get Leave", description = "get Leave from the database")
//    public List<Leave> getEmployeeLeaves(@PathVariable Long employeeId) {
//        return leaveService.getEmployeeLeaves(employeeId);
//    }
//
//    // 3. Approve or Reject Leave
//    @PutMapping("/status/{leaveId}/{status}")
//    @Operation(summary = "Update Leave", description = "Update Leave into the database")
//    public Leave updateLeaveStatus(@PathVariable Long leaveId, @PathVariable LeaveStatus status) {
//        return leaveService.updateLeaveStatus(leaveId, status);
//    }
	
	
	
	//private final LeaveService leaveService;

    // Apply for leave
    @PostMapping("/apply")
    public ResponseEntity<String> applyLeave(@RequestParam Long userId,
                                             @RequestBody LeaveRequestDto dto) {
        String response = leaveService.applyLeave(userId, dto);
        return ResponseEntity.ok(response);
    }

    // Approve leave
    @PutMapping("/approve/{leaveId}")
    public ResponseEntity<String> approveLeave(@PathVariable Long leaveId,
                                               @RequestParam Long managerId) {
        String response = leaveService.approveLeave(leaveId, managerId);
        return ResponseEntity.ok(response);
    }

    // Reject leave
    @PutMapping("/reject/{leaveId}")
    public ResponseEntity<String> rejectLeave(@PathVariable Long leaveId,
                                              @RequestParam Long managerId,
                                              @RequestParam String reason) {
        String response = leaveService.rejectLeave(leaveId, managerId, reason);
        return ResponseEntity.ok(response);
    }

    // Get leaves for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LeaveDto>> getLeavesByUser(@PathVariable Long userId) {
        List<LeaveDto> leaves = leaveService.getLeavesByUser(userId);
        return ResponseEntity.ok(leaves);
    }

    // Get all pending leaves (admin/moderator only)
    @GetMapping("/pending")
    public ResponseEntity<List<LeaveDto>> getAllPendingLeaves() {
        List<LeaveDto> pendingLeaves = leaveService.getAllPendingLeaves();
        return ResponseEntity.ok(pendingLeaves);
    }
    
    
    
}
