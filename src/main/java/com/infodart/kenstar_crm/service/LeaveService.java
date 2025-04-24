package com.infodart.kenstar_crm.service;

import java.util.List;

import com.infodart.kenstar_crm.dto.LeaveDto;
import com.infodart.kenstar_crm.dto.LeaveRequestDto;
import com.infodart.kenstar_crm.entity.Leave;
import com.infodart.kenstar_crm.enums.LeaveStatus;

public interface LeaveService {

//	public Leave applyForLeave(Long employeeId, Leave leaveRequest);
//
//	public List<Leave> getEmployeeLeaves(Long employeeId);
//
//	public Leave updateLeaveStatus(Long leaveId, LeaveStatus status);
	
	public String applyLeave(Long userId, LeaveRequestDto dto);
	
	public String approveLeave(Long leaveId, Long approverId);
	
	public String rejectLeave(Long leaveId, Long approverId, String reason);
	
	public List<LeaveDto> getLeavesByUser(Long userId);
	
	public List<LeaveDto> getAllPendingLeaves();
}
