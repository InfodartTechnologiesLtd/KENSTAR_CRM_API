package com.infodart.kenstar_crm.mapper;

import com.infodart.kenstar_crm.dto.LeaveBalanceDto;
import com.infodart.kenstar_crm.entity.LeaveBalance;

public class LeaveBalanceMapper {

	
	public static LeaveBalanceDto toDto(LeaveBalance leave) {
        if (leave == null) return null;

        LeaveBalanceDto dto = new LeaveBalanceDto();
        dto.setId(leave.getId());
        dto.setUserId(leave.getUserId());
        dto.setTotalLeaves(leave.getTotalLeaves());
        dto.setUsedLeaves(leave.getUsedLeaves());
        dto.setRemainingLeaves(leave.getRemainingLeaves());
        dto.setExpiredLeaves(leave.getExpiredLeaves());
       
        return dto;
    }

    public static LeaveBalance toEntity(LeaveBalanceDto dto) {
        if (dto == null) return null;

        LeaveBalance leave = new LeaveBalance();
         
        leave.setUserId(dto.getUserId());
        leave.setTotalLeaves(dto.getTotalLeaves());
        leave.setUsedLeaves(dto.getUsedLeaves());
        leave.setRemainingLeaves(dto.getRemainingLeaves());
        leave.setExpiredLeaves(dto.getExpiredLeaves());
        
        return leave;
    }
    
}
