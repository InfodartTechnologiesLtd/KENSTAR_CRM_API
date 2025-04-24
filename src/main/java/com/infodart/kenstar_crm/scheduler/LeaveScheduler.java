package com.infodart.kenstar_crm.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.infodart.kenstar_crm.entity.LeaveBalance;
import com.infodart.kenstar_crm.entity.User;
import com.infodart.kenstar_crm.repository.LeaveBalanceRepository;
import com.infodart.kenstar_crm.repository.UserRepository;

@Component
public class LeaveScheduler {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LeaveBalanceRepository leaveBalanceRepository;
	// private static final Logger logger =
	// LoggerFactory.getLogger(LeaveScheduler.class);

	public LeaveScheduler(UserRepository userRepository, LeaveBalanceRepository leaveBalanceRepository) {
		this.userRepository = userRepository;
		this.leaveBalanceRepository = leaveBalanceRepository;
	}

	@Scheduled(cron = "0 0 1 1 * ?") // Runs on 1st of every month at 1:00 AM
	public void allocateMonthlyLeaves() {
		// logger.info("Running Monthly Leave Allocation Job...");
		List<User> users = userRepository.findAll();

		for (User user : users) {
			LeaveBalance balance = leaveBalanceRepository.findByUserId(user.getId()).orElseGet(() -> {
				LeaveBalance b = new LeaveBalance();
				b.setUser(user.getId());
				b.setTotalLeaves(0);
				b.setUsedLeaves(0);
				b.setRemainingLeaves(0);
				return b;
			});

			balance.setTotalLeaves(balance.getTotalLeaves() + 2);
			balance.setRemainingLeaves(balance.getRemainingLeaves() + 2);

			leaveBalanceRepository.save(balance);
			// logger.info("Added 2 leaves to userId {}: total={}, remaining={}",
			// user.getId(), balance.getTotalLeaves(), balance.getRemainingLeaves());
		}

		// logger.info("Monthly Allocation Done.");
	}

	@Scheduled(cron = "0 0 2 1 1 ?") // Jan 1st, 2:00 AM
	public void carryForwardUnusedLeaves() {
		// logger.info("Running Yearly Carry-Forward Job...");

		List<LeaveBalance> allBalances = leaveBalanceRepository.findAll();
		int carryForwardLimit = 12;

		for (LeaveBalance balance : allBalances) {
			int remaining = balance.getRemainingLeaves();
			int carried = Math.min(remaining, carryForwardLimit);
			int expired = remaining - carried;

			balance.setTotalLeaves(carried); // reset total to carried leaves
			balance.setRemainingLeaves(carried); // reset remaining
			balance.setUsedLeaves(0); // reset used
			balance.setExpiredLeaves(expired); // save expired count

			leaveBalanceRepository.save(balance);

			// logger.info("Carry Forward for userId {}: carried={}, expired={}",
			// balance.getUser().getId(), carried, expired);
		}

		// logger.info("Yearly Carry-Forward Job Completed.");
	}

}
