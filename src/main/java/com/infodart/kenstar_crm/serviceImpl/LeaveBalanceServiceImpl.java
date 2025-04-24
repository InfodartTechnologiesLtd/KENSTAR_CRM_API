package com.infodart.kenstar_crm.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infodart.kenstar_crm.entity.LeaveBalance;
import com.infodart.kenstar_crm.entity.User;
import com.infodart.kenstar_crm.repository.LeaveBalanceRepository;
import com.infodart.kenstar_crm.repository.UserRepository;
import com.infodart.kenstar_crm.service.LeaveBalanceService;

@Service
public class LeaveBalanceServiceImpl implements LeaveBalanceService {

	@Autowired
	private LeaveBalanceRepository leaveBalanceRepository;

	@Autowired
	private UserRepository userRepository;

	public LeaveBalance createOrInitializeBalance(Long userId) {
		if (leaveBalanceRepository.findByUserId(userId).isPresent()) {
			throw new RuntimeException("Leave balance already exists for user.");
		}

		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		LeaveBalance balance = new LeaveBalance(user.getId(), 24, 0,24, 0);

		return leaveBalanceRepository.save(balance);
	}

	public LeaveBalance getBalanceByUserId(Long userId) {
		return leaveBalanceRepository.findByUserId(userId)
				.orElseThrow(() -> new RuntimeException("Leave balance not found"));
	}

}
