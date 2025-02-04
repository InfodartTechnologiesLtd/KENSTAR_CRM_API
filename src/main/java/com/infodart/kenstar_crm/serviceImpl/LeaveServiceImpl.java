package com.infodart.kenstar_crm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infodart.kenstar_crm.entity.Leave;
import com.infodart.kenstar_crm.entity.LeaveStatus;
import com.infodart.kenstar_crm.repository.LeaveRepository;
import com.infodart.kenstar_crm.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private LeaveRepository leaveRepository;

	// Apply for leave
	public Leave applyForLeave(Long employeeId, Leave leaveRequest) {
		leaveRequest.setEmployeeId(employeeId);
		leaveRequest.setStatus(LeaveStatus.PENDING);
		return leaveRepository.save(leaveRequest);
	}

	// Get employee leave history
	public List<Leave> getEmployeeLeaves(Long employeeId) {
		return leaveRepository.findByEmployeeId(employeeId);
	}

	// Approve or reject leave request
	public Leave updateLeaveStatus(Long leaveId, LeaveStatus status) {
		Leave leave = leaveRepository.findById(leaveId).orElseThrow(() -> new RuntimeException("Leave not found"));
		leave.setStatus(status);
		return leaveRepository.save(leave);
	}

}
