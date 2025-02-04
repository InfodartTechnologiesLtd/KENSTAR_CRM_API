package com.infodart.kenstar_crm.dto;

import java.time.LocalDate;

import com.infodart.kenstar_crm.entity.LeaveStatus;
import com.infodart.kenstar_crm.entity.LeaveType;

public class LeaveDto {

	private Long id;

	private Long employeeId;

	private LeaveType leaveType;

	private LocalDate startDate;
	private LocalDate endDate;
	private boolean halfDay;

	private LeaveStatus status;

	public LeaveDto() {
		super();
	}

	public LeaveDto(Long id, Long employeeId, LeaveType leaveType, LocalDate startDate, LocalDate endDate,
			boolean halfDay, LeaveStatus status) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.halfDay = halfDay;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isHalfDay() {
		return halfDay;
	}

	public void setHalfDay(boolean halfDay) {
		this.halfDay = halfDay;
	}

	public LeaveStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LeaveDto [id=" + id + ", employeeId=" + employeeId + ", leaveType=" + leaveType + ", startDate="
				+ startDate + ", endDate=" + endDate + ", halfDay=" + halfDay + ", status=" + status + "]";
	}

	 
	
	
	

}
