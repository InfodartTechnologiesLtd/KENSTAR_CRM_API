package com.infodart.kenstar_crm.mapper;


import com.infodart.kenstar_crm.entity.Leave;
import com.infodart.kenstar_crm.dto.LeaveDto;

public class LeaveMapper {

	
	public static LeaveDto toDto(Leave leave) {
        if (leave == null) return null;

        LeaveDto dto = new LeaveDto();
        dto.setId(leave.getLeaveId());
        dto.setUserId(leave.getUserId());
        dto.setLeaveType(leave.getLeaveType());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setReason(leave.getReason());
        dto.setLeaveStatus(leave.getLeaveStatus());
        dto.setTotalDays(leave.getTotalDays());
        dto.setBalanceBefore(leave.getBalanceBefore());
        dto.setBalanceAfter(leave.getBalanceAfter());
        dto.setApprovedBy(leave.getApprovedBy());
        dto.setApprovedAt(leave.getApprovedAt());
        dto.setRejectionReason(leave.getRejectionReason());

        return dto;
    }

    public static Leave toEntity(LeaveDto dto) {
        if (dto == null) return null;

        Leave leave = new Leave();
        leave.setLeaveId(dto.getId());
        leave.setUserId(dto.getUserId());
        leave.setLeaveType(dto.getLeaveType());
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setReason(dto.getReason());
        leave.setLeaveStatus(dto.getLeaveStatus());
        leave.setTotalDays(dto.getTotalDays());
        leave.setBalanceBefore(dto.getBalanceBefore());
        leave.setBalanceAfter(dto.getBalanceAfter());
        leave.setApprovedBy(dto.getApprovedBy());
        leave.setApprovedAt(dto.getApprovedAt());
        leave.setRejectionReason(dto.getRejectionReason());

        return leave;
    }
}
