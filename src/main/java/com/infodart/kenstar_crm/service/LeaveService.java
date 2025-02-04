package com.infodart.kenstar_crm.service;

import java.util.List;

import com.infodart.kenstar_crm.entity.Leave;
import com.infodart.kenstar_crm.entity.LeaveStatus;

public interface LeaveService {

	public Leave applyForLeave(Long employeeId, Leave leaveRequest);

	public List<Leave> getEmployeeLeaves(Long employeeId);

	public Leave updateLeaveStatus(Long leaveId, LeaveStatus status);
}
