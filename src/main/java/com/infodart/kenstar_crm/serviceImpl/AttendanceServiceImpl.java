package com.infodart.kenstar_crm.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infodart.kenstar_crm.dto.AttendanceDto;
import com.infodart.kenstar_crm.dto.AttendanceSummaryDto;
import com.infodart.kenstar_crm.entity.Attendance;
import com.infodart.kenstar_crm.entity.Holiday;
import com.infodart.kenstar_crm.entity.User;
import com.infodart.kenstar_crm.enums.AttendanceStatus;
import com.infodart.kenstar_crm.enums.AttendanceType;
import com.infodart.kenstar_crm.mapper.AttendanceMapper;
import com.infodart.kenstar_crm.repository.AttendanceRepository;
import com.infodart.kenstar_crm.repository.HolidayRepository;
import com.infodart.kenstar_crm.repository.UserRepository;
import com.infodart.kenstar_crm.service.AttendanceService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private HolidayRepository holidayRepository;

	// Mark Attendance In
//	public Attendance markAttendanceIn(Long employeeId) {
//		LocalDate today = LocalDate.now();
//
//		// Check if already marked in
//		List<Attendance> existingAttendance = attendanceRepository.findByEmployeeIdAndAttendanceDate(employeeId, today);
//		if (!existingAttendance.isEmpty()) {
//			throw new RuntimeException("Already marked in for today.");
//		}
//
//		Attendance attendance = new Attendance(employeeId, today, LocalDateTime.now());
//		return attendanceRepository.save(attendance);
//	}
//
//	// Mark Attendance Out
//	public Attendance markAttendanceOut(Long employeeId) {
//		LocalDate today = LocalDate.now();
//
//		List<Attendance> existingAttendance = attendanceRepository.findByEmployeeIdAndAttendanceDate(employeeId, today);
//		if (existingAttendance.isEmpty()) {
//			throw new RuntimeException("Attendance not marked in for today.");
//		}
//		Attendance attendance = null;
//		if (existingAttendance.size() > 0) {
//			attendance = existingAttendance.get(0);
//			attendance.setOutTime(LocalDateTime.now());
//		} else {
//
//			// no attendance found to punch out
//		}
//		return attendanceRepository.save(attendance);
//	}
//
//	// Get Last 7 Days Attendance
//	public List<Attendance> getLast7DaysAttendance(Long employeeId) {
//		LocalDate today = LocalDate.now();
//		LocalDate startDate = today.minusDays(6);
//		return attendanceRepository.findByEmployeeIdAndAttendanceDateBetween(employeeId, startDate, today);
//	}

	// private final AttendanceRepository attendanceRepository;

	@Autowired
	private UserRepository userRepository;

//	@Override
//	public AttendanceDto markAttendanceOld(Long userId, AttendanceDto dto) {
//		User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
//
//		// Check if already marked
//		Attendance existing = attendanceRepository.findByUserIdAndDate(userId, dto.getDate()).orElse(null);
//		if (existing != null) {
//			if (existing.getCheckInTime() != null && existing.getCheckOutTime() == null
//					&& dto.getCheckOutTime() != null) {
//				existing.setCheckOutTime(dto.getCheckOutTime());
//				Duration duration = Duration.between(existing.getCheckInTime(), dto.getCheckOutTime());
//				existing.setWorkingHours((double) duration.toHours());
//
//				if (duration.toHours() >= 9) {
//					existing.setIsApproved(true);
//					existing.setAttendanceStatus(AttendanceStatus.APPROVED);
//				} else {
//					existing.setIsApproved(false);
//					existing.setAttendanceStatus(AttendanceStatus.PENDING);
//				}
//				return AttendanceMapper.toDto(attendanceRepository.save(existing));
//			}
//			return AttendanceMapper.toDto(existing);
//		}
//
//		Attendance attendance = AttendanceMapper.toEntity(dto, user);
//
//		if (dto.getCheckInTime() != null && dto.getCheckOutTime() != null) {
//			Duration duration = Duration.between(dto.getCheckInTime(), dto.getCheckOutTime());
//			attendance.setWorkingHours((double) duration.toHours());
//
//			if (duration.toHours() >= 9) {
//				attendance.setIsApproved(true);
//				attendance.setAttendanceStatus(AttendanceStatus.APPROVED);
//			} else {
//				attendance.setIsApproved(false);
//				attendance.setAttendanceStatus(AttendanceStatus.PENDING);
//			}
//		}
//
//		return AttendanceMapper.toDto(attendanceRepository.save(attendance));
//	}

	@Override
	public AttendanceDto markAttendance(Long userId, AttendanceDto dto) {
		User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

		LocalDate attendanceDate = dto.getDate() != null ? dto.getDate() : LocalDate.now();
		LocalTime checkInTime = dto.getCheckInTime();
		LocalTime checkOutTime = dto.getCheckOutTime();
		DayOfWeek dayOfWeek = attendanceDate.getDayOfWeek();

		// Check if already marked
		Attendance existing = attendanceRepository.findByUserIdAndDate(userId, attendanceDate).orElse(null);

		// WEEKEND auto mark
		if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
			if (existing == null) {
				Attendance weekendAttendance = new Attendance();
				weekendAttendance.setUserId(userId);
				weekendAttendance.setDate(attendanceDate);
				weekendAttendance.setDayName(dto.getDayName());
				weekendAttendance.setAttendanceType(AttendanceType.WEEK_OFF);
				weekendAttendance.setWorkingHours(0.0);
				weekendAttendance.setIsApproved(true);
				weekendAttendance.setAttendanceStatus(AttendanceStatus.APPROVED);
				return AttendanceMapper.toDto(attendanceRepository.save(weekendAttendance));
			}
			return AttendanceMapper.toDto(existing);
		}

		// HOLIDAY auto mark
		
		Optional<Holiday> optionalHoliday = holidayRepository.findByDate(attendanceDate);
		if (!optionalHoliday.isEmpty()) {
			 
		 
		
		//if (holidayRepository.existsByDate(attendanceDate)) {
			if (existing == null) {
				Attendance holidayAttendance = new Attendance();
				holidayAttendance.setUserId(userId);
				holidayAttendance.setDate(attendanceDate);
				holidayAttendance.setDayName(dto.getDayName());
				holidayAttendance.setAttendanceType(AttendanceType.HOLIDAY);
				holidayAttendance.setWorkingHours(0.0);
				holidayAttendance.setIsApproved(true);
				holidayAttendance.setAttendanceStatus(AttendanceStatus.APPROVED);
				return AttendanceMapper.toDto(attendanceRepository.save(holidayAttendance));
			}
			return AttendanceMapper.toDto(existing);
		}

		// Already marked check-in? Now check-out
		if (existing != null) {
			if (existing.getCheckInTime() != null && existing.getCheckOutTime() == null && checkOutTime != null) {
				existing.setCheckOutTime(checkOutTime);
				Duration duration = Duration.between(existing.getCheckInTime(), checkOutTime);
				double hours = duration.toHours();
				existing.setWorkingHours(hours);

				if (hours >= 9) {
					existing.setIsApproved(true);
					existing.setAttendanceStatus(AttendanceStatus.APPROVED);
				} else {
					existing.setIsApproved(false);
					existing.setAttendanceStatus(AttendanceStatus.PENDING);
				}
				return AttendanceMapper.toDto(attendanceRepository.save(existing));
			}
			return AttendanceMapper.toDto(existing);
		}

		// First-time mark (check-in)
		Attendance attendance = AttendanceMapper.toEntity(dto, user);
		attendance.setDate(attendanceDate);
		attendance.setDayName(dto.getDayName());

		if (checkInTime != null && checkOutTime != null) {
			Duration duration = Duration.between(checkInTime, checkOutTime);
			double hours = duration.toHours();
			attendance.setWorkingHours(hours);

			if (hours >= 9) {
				attendance.setIsApproved(true);
				attendance.setAttendanceStatus(AttendanceStatus.APPROVED);
			} else {
				attendance.setIsApproved(false);
				attendance.setAttendanceStatus(AttendanceStatus.PENDING);
			}
		} else {
			attendance.setWorkingHours(0.0);
			attendance.setAttendanceStatus(AttendanceStatus.PENDING);
		}

		return AttendanceMapper.toDto(attendanceRepository.save(attendance));
	}

	@Override
	public List<AttendanceDto> getAllAttendanceByUser(Long userId) {
		return attendanceRepository.findByUserId(userId).stream().map(AttendanceMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public AttendanceDto getAttendanceById(Long id) {
		Attendance attendance = attendanceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
		return AttendanceMapper.toDto(attendance);
	}

	@Override
	public AttendanceDto updateAttendance(Long id, AttendanceDto dto) {
		Attendance attendance = attendanceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Attendance not found"));

		attendance.setCheckInTime(dto.getCheckInTime());
		attendance.setCheckOutTime(dto.getCheckOutTime());
		attendance.setDayName(dto.getDayName());
		attendance.setAttendanceType(dto.getAttendanceType());

		if (dto.getCheckInTime() != null && dto.getCheckOutTime() != null) {
			Duration duration = Duration.between(dto.getCheckInTime(), dto.getCheckOutTime());
			attendance.setWorkingHours((double) duration.toHours());

			if (duration.toHours() >= 9) {
				attendance.setIsApproved(true);
				attendance.setAttendanceStatus(AttendanceStatus.APPROVED);
			} else {
				attendance.setIsApproved(false);
				attendance.setAttendanceStatus(AttendanceStatus.PENDING);
			}
		}

		return AttendanceMapper.toDto(attendanceRepository.save(attendance));
	}

	@Override
	public void deleteAttendance(Long id) {
		Attendance attendance = attendanceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
		attendanceRepository.delete(attendance);
	}

	@Override
	public AttendanceSummaryDto getAttendanceSummary(Long userId) {
		List<Attendance> attendanceList = attendanceRepository.findByUserId(userId);
		long present = attendanceList.stream().filter(a -> a.getAttendanceType() == AttendanceType.PRESENT).count();
		long half = attendanceList.stream().filter(a -> a.getAttendanceType() == AttendanceType.HALF_DAY).count();
		long leave = attendanceList.stream().filter(a -> a.getAttendanceType() == AttendanceType.LEAVE
				|| a.getAttendanceType() == AttendanceType.HALF_DAY_LEAVE).count();

		return AttendanceMapper.toSummaryDto(present, half, leave, attendanceList.size());
	}

	@Override
	public AttendanceDto approveAttendance(Long id) {
		Attendance attendance = attendanceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
		attendance.setIsApproved(true);
		attendance.setAttendanceStatus(AttendanceStatus.APPROVED);
		return AttendanceMapper.toDto(attendanceRepository.save(attendance));
	}

	@Override
	public AttendanceDto rejectAttendance(Long id) {
		Attendance attendance = attendanceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
		attendance.setIsApproved(false);
		attendance.setAttendanceStatus(AttendanceStatus.REJECTED);
		return AttendanceMapper.toDto(attendanceRepository.save(attendance));
	}

	// @@@@@@@@@@@@@@@@@
	@Override
	public AttendanceDto getAttendanceByDate(Long userId, LocalDate date) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		Attendance attendance = attendanceRepository.findByUserIdAndDate(user.getId(), date)
				.orElseThrow(() -> new RuntimeException("Attendance not found for given date."));

		return AttendanceMapper.toDto(attendance);
	}

	@Override
	public AttendanceSummaryDto getSummary(Long userId, int month, int year) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		List<Attendance> attendanceList = attendanceRepository.findByUserIdAndMonthAndYear(user.getId(), month, year);

		long presentCount = attendanceList.stream().filter(a -> a.getAttendanceType() == AttendanceType.PRESENT)
				.count();

		long halfDayCount = attendanceList.stream().filter(a -> a.getAttendanceType() == AttendanceType.HALF_DAY)
				.count();

		long leaveCount = attendanceList.stream().filter(a -> a.getAttendanceType() == AttendanceType.LEAVE).count();

		AttendanceSummaryDto summary = AttendanceMapper.toSummaryDto(presentCount, halfDayCount, leaveCount,
				attendanceList.size());

		return summary;
	}

	@Override
	public List<AttendanceDto> getPendingForApproval() {
		// List<Attendance> pendingList =
		// attendanceRepository.findByAttendanceStatus(AttendanceStatus.PENDING);
		// return pendingList.stream().map(this::mapToDto).collect(Collectors.toList());

		List<Attendance> pendingList = attendanceRepository.findByAttendanceStatus(AttendanceStatus.PENDING);
		return pendingList.stream().map(AttendanceMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public List<AttendanceDto> getAllAttendance() {
		List<Attendance> allList = (List<Attendance>) attendanceRepository.findAll();
		return allList.stream().map(AttendanceMapper::toDto).collect(Collectors.toList());
	}

	// private boolean isHoliday(LocalDate date) {
	// return holidayRepository.findByDate(date).isPresent();
	// }
}
