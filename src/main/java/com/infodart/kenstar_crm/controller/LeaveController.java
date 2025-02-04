package com.infodart.kenstar_crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.kenstar_crm.entity.Leave;
import com.infodart.kenstar_crm.entity.LeaveStatus;
import com.infodart.kenstar_crm.service.LeaveService;

@RestController
@RequestMapping("/leaveManage")
public class LeaveController {

	
	@Autowired
    private LeaveService leaveService;

    // 1. Apply for Leave
    @PostMapping("/apply/{employeeId}")
    public Leave applyForLeave(@PathVariable Long employeeId, @RequestBody Leave leaveRequest) {
        return leaveService.applyForLeave(employeeId, leaveRequest);
    }

    // 2. Get Employee Leave History
    @GetMapping("/history/{employeeId}")
    public List<Leave> getEmployeeLeaves(@PathVariable Long employeeId) {
        return leaveService.getEmployeeLeaves(employeeId);
    }

    // 3. Approve or Reject Leave
    @PutMapping("/status/{leaveId}/{status}")
    public Leave updateLeaveStatus(@PathVariable Long leaveId, @PathVariable LeaveStatus status) {
        return leaveService.updateLeaveStatus(leaveId, status);
    }
}
