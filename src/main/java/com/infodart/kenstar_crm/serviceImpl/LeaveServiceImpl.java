package com.infodart.kenstar_crm.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.infodart.kenstar_crm.entity.Leave;
import com.infodart.kenstar_crm.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Override
	public List<Leave> getAllLeaveByEmpId(Long employeeId) {
		List<Leave> leave = new ArrayList<>();
		return leave;
	}

}
