package com.infodart.kenstar_crm.serviceImpl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infodart.kenstar_crm.dto.LeaveDto;
import com.infodart.kenstar_crm.dto.LeaveRequestDto;
import com.infodart.kenstar_crm.entity.Attendance;
import com.infodart.kenstar_crm.entity.Leave;
import com.infodart.kenstar_crm.entity.LeaveBalance;
import com.infodart.kenstar_crm.entity.User;
import com.infodart.kenstar_crm.enums.LeaveStatus;
import com.infodart.kenstar_crm.enums.LeaveType;
import com.infodart.kenstar_crm.mapper.AttendanceMapper;
import com.infodart.kenstar_crm.mapper.HolidayMapper;
import com.infodart.kenstar_crm.mapper.LeaveMapper;
import com.infodart.kenstar_crm.repository.LeaveBalanceRepository;
import com.infodart.kenstar_crm.repository.LeaveRepository;
import com.infodart.kenstar_crm.repository.UserRepository;
import com.infodart.kenstar_crm.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {

//	@Autowired
//	private LeaveRepository leaveRepository;

	// Apply for leave
//	public Leave applyForLeave(Long employeeId, Leave leaveRequest) {
//		leaveRequest.setEmployeeId(employeeId);
//		leaveRequest.setStatus(LeaveStatus.PENDING);
//		return leaveRepository.save(leaveRequest);
//	}
//
//	// Get employee leave history
//	public List<Leave> getEmployeeLeaves(Long employeeId) {
//		return leaveRepository.findByEmployeeId(employeeId);
//	}
//
//	// Approve or reject leave request
//	public Leave updateLeaveStatus(Long leaveId, LeaveStatus status) {
//		Leave leave = leaveRepository.findById(leaveId).orElseThrow(() -> new RuntimeException("Leave not found"));
//		leave.setStatus(status);
//		return leaveRepository.save(leave);
//	}

	@Autowired
	private LeaveRepository leaveRepository;

	@Autowired
	private LeaveBalanceRepository leaveBalanceRepository;

	@Autowired
	private UserRepository userRepository;

	public String applyLeave(Long userId, LeaveRequestDto dto) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		if (dto.getStartDate().isAfter(dto.getEndDate())) {
			throw new IllegalArgumentException("Start date cannot be after end date");
		}

		// Calculate total leave days
		long days = ChronoUnit.DAYS.between(dto.getStartDate().toLocalDate(), dto.getEndDate().toLocalDate()) + 1;

		LeaveBalance leaveBalance = leaveBalanceRepository.findByUserId(userId)
				.orElseThrow(() -> new RuntimeException("Leave balance not found"));

		Leave leave = new Leave();
		leave.setUserId(user.getId());
		leave.setLeaveType(dto.getLeaveType());
		leave.setStartDate(dto.getStartDate());
		leave.setEndDate(dto.getEndDate());
		leave.setReason(dto.getReason());
		leave.setLeaveStatus(LeaveStatus.PENDING);
		leave.setTotalDays((int) days);
		leave.setBalanceBefore(leaveBalance.getRemainingLeaves());

		if (leaveBalance.getRemainingLeaves() >= days) {
			leave.setBalanceAfter(leaveBalance.getRemainingLeaves() - (int) days);
			leaveBalance.setUsedLeaves(leaveBalance.getUsedLeaves() + (int) days);
			leaveBalance.setRemainingLeaves(leaveBalance.getTotalLeaves() - leaveBalance.getUsedLeaves());
		} else {
			// If not enough leaves, consider unpaid
			leave.setLeaveType(LeaveType.UNPAID_LEAVE);
			leave.setBalanceAfter(leaveBalance.getRemainingLeaves());
		}

		leaveRepository.save(leave);
		leaveBalanceRepository.save(leaveBalance);

		return "Leave request submitted successfully.";
	}

	public String approveLeave(Long leaveId, Long approverId) {
		Leave leave = leaveRepository.findById(leaveId).orElseThrow(() -> new RuntimeException("Leave not found"));

		if (leave.getLeaveStatus() != LeaveStatus.PENDING) {
			throw new IllegalStateException("Leave already processed");
		}

		leave.setLeaveStatus(LeaveStatus.APPROVED);
		leave.setApprovedBy(approverId); // assuming you want to track who approved
		leave.setApprovedAt(LocalDateTime.now());

		// Update leave balance if it's not unpaid leave
		if (leave.getLeaveType() != LeaveType.UNPAID_LEAVE) {
			LeaveBalance balance = leaveBalanceRepository.findByUserId(leave.getUserId())
					.orElseThrow(() -> new RuntimeException("Leave balance not found"));

			balance.setUsedLeaves(balance.getUsedLeaves() + leave.getTotalDays());
			balance.setRemainingLeaves(balance.getTotalLeaves() - balance.getUsedLeaves());

			leave.setBalanceAfter(balance.getRemainingLeaves());
			leaveBalanceRepository.save(balance);
		}

		leaveRepository.save(leave);
		return "Leave approved successfully.";
	}

	public String rejectLeave(Long leaveId, Long approverId, String reason) {
		Leave leave = leaveRepository.findById(leaveId).orElseThrow(() -> new RuntimeException("Leave not found"));

		if (leave.getLeaveStatus() != LeaveStatus.PENDING) {
			throw new IllegalStateException("Leave already processed");
		}

		leave.setLeaveStatus(LeaveStatus.REJECTED);
		leave.setApprovedBy(approverId);
		leave.setApprovedAt(LocalDateTime.now());
		leave.setRejectionReason(reason);
		leave.setBalanceAfter(leave.getBalanceBefore()); // No deduction

		leaveRepository.save(leave);
		return "Leave rejected successfully.";
	}

	public List<LeaveDto> getLeavesByUser(Long userId) {

		List<Leave> leaveList = leaveRepository.findAllByUserId(userId);
		return leaveList.stream().map(LeaveMapper::toDto).collect(Collectors.toList());
	}

	public List<LeaveDto> getAllPendingLeaves() {
		List<Leave> leaveList = leaveRepository.findAllByLeaveStatus(LeaveStatus.PENDING);
		return leaveList.stream().map(LeaveMapper::toDto).collect(Collectors.toList());
	}

}
