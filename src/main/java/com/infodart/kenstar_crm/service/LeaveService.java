package com.infodart.kenstar_crm.service;

import java.util.List;

import com.infodart.kenstar_crm.dto.LeaveDto;
import com.infodart.kenstar_crm.dto.LeaveRequestDto;

public interface LeaveService {

	public LeaveDto applyLeave(Long userId, LeaveRequestDto dto);

	public LeaveDto approveLeave(Long leaveId, Long approverId);

	public LeaveDto rejectLeave(Long leaveId, Long approverId, String reason);

	public List<LeaveDto> getLeavesByUser(Long userId);

	public List<LeaveDto> getAllPendingLeaves();
}
