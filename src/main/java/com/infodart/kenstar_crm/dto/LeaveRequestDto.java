package com.infodart.kenstar_crm.dto;

import java.time.LocalDateTime;

import com.infodart.kenstar_crm.enums.LeaveType;

public class LeaveRequestDto {

	private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LeaveType leaveType;
    private String reason;
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public LeaveType getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
    
    
    
}
