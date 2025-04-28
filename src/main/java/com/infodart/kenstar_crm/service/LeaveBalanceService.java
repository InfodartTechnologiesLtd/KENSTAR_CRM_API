package com.infodart.kenstar_crm.service;

import com.infodart.kenstar_crm.dto.LeaveBalanceDto;

public interface LeaveBalanceService {

	public LeaveBalanceDto createOrInitializeBalance(Long userId);

	public LeaveBalanceDto getBalanceByUserId(Long userId);
}
