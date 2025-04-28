package com.infodart.kenstar_crm.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infodart.kenstar_crm.entity.Attendance;
import com.infodart.kenstar_crm.enums.AttendanceStatus;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
	//List<Attendance> findByEmployeeIdAndAttendanceDate(Long employeeId, LocalDate date);

	//List<Attendance> findByEmployeeIdAndAttendanceDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);

	
	
	// Find attendance by userId and date (unique for a user on a given date)
    Optional<Attendance> findByUserIdAndDate(Long userId, LocalDate date);

    // Get all attendance records by userId
    List<Attendance> findByUserId(Long userId);

    // Find attendance by attendanceStatus (e.g. Pending, Approved, Rejected)
    List<Attendance> findByAttendanceStatus(AttendanceStatus status);

    // Get attendance by Id
    Optional<Attendance> findById(Long id);

	List<Attendance> findByUserIdAndMonthAndYear(Long userId, int month, int year);

	//Optional<Attendance> findByUserIdAndDate(Long userId, LocalDate date);  

    // Get attendance summary based on userId (optional: can be added if needed)
    // List<Attendance> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

	boolean existsByDate(LocalDate date);

	boolean existsByUserIdAndDate(Long id, LocalDate today);

	List<Attendance> findByUserIdAndDateBetween(Long employeeId, LocalDate startDate, LocalDate today);  
}
