package com.infodart.kenstar_crm.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.infodart.kenstar_crm.enums.LeaveStatus;
import com.infodart.kenstar_crm.enums.LeaveType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class LeaveDto {

	private Long id;

	private Long userId;

	private LeaveType leaveType;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private String reason;

	private LeaveStatus leaveStatus;

	private int totalDays; // Can be calculated as difference between start and end

	private int balanceBefore;
	private int balanceAfter;

	private Long approvedBy;
	private LocalDateTime approvedAt;
	private String rejectionReason;

	public LeaveDto() {
		super();
	}

	
	
	public LeaveDto(Long id, Long userId, LeaveType leaveType, LocalDateTime startDate, LocalDateTime endDate,
			String reason, LeaveStatus leaveStatus, int totalDays, int balanceBefore, int balanceAfter, Long approvedBy,
			LocalDateTime approvedAt, String rejectionReason) {
		super();
		this.id = id;
		this.userId = userId;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.leaveStatus = leaveStatus;
		this.totalDays = totalDays;
		this.balanceBefore = balanceBefore;
		this.balanceAfter = balanceAfter;
		this.approvedBy = approvedBy;
		this.approvedAt = approvedAt;
		this.rejectionReason = rejectionReason;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LeaveStatus getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(LeaveStatus leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public int getBalanceBefore() {
		return balanceBefore;
	}

	public void setBalanceBefore(int balanceBefore) {
		this.balanceBefore = balanceBefore;
	}

	public int getBalanceAfter() {
		return balanceAfter;
	}

	public void setBalanceAfter(int balanceAfter) {
		this.balanceAfter = balanceAfter;
	}

	public Long getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Long approvedBy) {
		this.approvedBy = approvedBy;
	}

	public LocalDateTime getApprovedAt() {
		return approvedAt;
	}

	public void setApprovedAt(LocalDateTime approvedAt) {
		this.approvedAt = approvedAt;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

}
