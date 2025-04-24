package com.infodart.kenstar_crm.scheduler;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.infodart.kenstar_crm.entity.User;
import com.infodart.kenstar_crm.entity.Attendance;
import com.infodart.kenstar_crm.entity.Holiday;
import com.infodart.kenstar_crm.enums.AttendanceStatus;
import com.infodart.kenstar_crm.enums.AttendanceType;
import com.infodart.kenstar_crm.repository.AttendanceRepository;
import com.infodart.kenstar_crm.repository.HolidayRepository;
import com.infodart.kenstar_crm.repository.UserRepository;

@Component
public class AutoAttendanceScheduler {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private HolidayRepository holidayRepository;

	@Scheduled(cron = "0 0 2 * * ?") // Runs daily at 2 AM
	public void autoMarkAttendanceForWeekOffsAndHolidays() {
		LocalDate today = LocalDate.now();
		DayOfWeek dayOfWeek = today.getDayOfWeek();

		// Get all users
		List<User> users = userRepository.findAll();

		// Check if today is a holiday
		
		boolean isHoliday = true;//holidayRepository.existsByDate(today);
		
		Optional<Holiday> optionalHoliday = holidayRepository.findByDate(today);
		if (optionalHoliday.isEmpty()) {
			isHoliday = false;
		}

		// If already marked, skip
		for (User user : users) {
			boolean alreadyMarked = attendanceRepository.existsByUserIdAndDate(user.getId(), today);
			if (alreadyMarked)
				continue;

			Attendance attendance = new Attendance();
			attendance.setUserId(user.getId());
			attendance.setDate(today);
			attendance.setCreatedDate(LocalDateTime.now());
			attendance.setCreatedBy(user.getId());

			if (isHoliday) {
				attendance.setAttendanceType(AttendanceType.HOLIDAY);
				attendance.setAttendanceStatus(AttendanceStatus.APPROVED);
				attendance.setIsApproved(true);
			} else if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
				attendance.setAttendanceType(AttendanceType.WEEK_OFF);
				attendance.setAttendanceStatus(AttendanceStatus.APPROVED);
				attendance.setIsApproved(true);
			} else {
				continue;
			}

			attendanceRepository.save(attendance);
		}
	}
}
