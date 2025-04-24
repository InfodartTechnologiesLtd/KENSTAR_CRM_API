package com.infodart.kenstar_crm.service;

import com.infodart.kenstar_crm.entity.LeaveBalance;

public interface LeaveBalanceService {

	public LeaveBalance createOrInitializeBalance(Long userId);

	public LeaveBalance getBalanceByUserId(Long userId);
}
