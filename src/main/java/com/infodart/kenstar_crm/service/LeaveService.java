package com.infodart.kenstar_crm.service;

import java.util.List;

import com.infodart.kenstar_crm.entity.Attendance;
import com.infodart.kenstar_crm.entity.Leave;

public interface LeaveService {

	public List<Leave> getAllLeaveByEmpId(Long employeeId);

}
