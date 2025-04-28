package com.infodart.kenstar_crm.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infodart.kenstar_crm.dto.LeaveBalanceDto;
import com.infodart.kenstar_crm.entity.LeaveBalance;
import com.infodart.kenstar_crm.entity.User;
import com.infodart.kenstar_crm.exceptions.ResourceNotFoundException;
import com.infodart.kenstar_crm.exceptions.UserAlreadyExistException;
import com.infodart.kenstar_crm.exceptions.UserNotFoundException;
import com.infodart.kenstar_crm.mapper.LeaveBalanceMapper;
import com.infodart.kenstar_crm.repository.LeaveBalanceRepository;
import com.infodart.kenstar_crm.repository.UserRepository;
import com.infodart.kenstar_crm.service.LeaveBalanceService;

@Service
public class LeaveBalanceServiceImpl implements LeaveBalanceService {

	@Autowired
	private LeaveBalanceRepository leaveBalanceRepository;

	@Autowired
	private UserRepository userRepository;

	public LeaveBalanceDto createOrInitializeBalance(Long userId) {
		if (leaveBalanceRepository.findByUserId(userId).isPresent()) {
			throw new   UserAlreadyExistException("Leave balance already exists for user.");
		}

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		LeaveBalance balance = new LeaveBalance(user.getId(), 24, 0,24, 0);

		LeaveBalance leaveBalanceSaved = leaveBalanceRepository.save(balance);
		return LeaveBalanceMapper.toDto(leaveBalanceSaved);
	}

	public LeaveBalanceDto getBalanceByUserId(Long userId) {
		LeaveBalance leaveBalanceSaved = leaveBalanceRepository.findByUserId(userId)
				.orElseThrow(() -> new   ResourceNotFoundException("Leave balance not found"));
		
		return LeaveBalanceMapper.toDto(leaveBalanceSaved);
	}

}
