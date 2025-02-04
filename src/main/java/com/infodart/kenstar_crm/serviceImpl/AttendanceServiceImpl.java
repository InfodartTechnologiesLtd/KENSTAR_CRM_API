package com.infodart.kenstar_crm.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infodart.kenstar_crm.entity.Attendance;
import com.infodart.kenstar_crm.repository.AttendanceRepository;
import com.infodart.kenstar_crm.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	// Mark Attendance In
	public Attendance markAttendanceIn(Long employeeId) {
		LocalDate today = LocalDate.now();

		// Check if already marked in
		List<Attendance> existingAttendance = attendanceRepository.findByEmployeeIdAndAttendanceDate(employeeId, today);
		if (!existingAttendance.isEmpty()) {
			throw new RuntimeException("Already marked in for today.");
		}

		Attendance attendance = new Attendance(employeeId, today, LocalDateTime.now());
		return attendanceRepository.save(attendance);
	}

	// Mark Attendance Out
	public Attendance markAttendanceOut(Long employeeId) {
		LocalDate today = LocalDate.now();

		List<Attendance> existingAttendance = attendanceRepository.findByEmployeeIdAndAttendanceDate(employeeId, today);
		if (existingAttendance.isEmpty()) {
			throw new RuntimeException("Attendance not marked in for today.");
		}
		Attendance attendance = null;
		if (existingAttendance.size() > 0) {
			attendance = existingAttendance.get(0);
			attendance.setOutTime(LocalDateTime.now());
		} else {

			// no attendance found to punch out
		}
		return attendanceRepository.save(attendance);
	}

	// Get Last 7 Days Attendance
	public List<Attendance> getLast7DaysAttendance(Long employeeId) {
		LocalDate today = LocalDate.now();
		LocalDate startDate = today.minusDays(6);
		return attendanceRepository.findByEmployeeIdAndAttendanceDateBetween(employeeId, startDate, today);
	}

}
