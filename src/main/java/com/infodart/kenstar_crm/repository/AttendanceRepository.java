package com.infodart.kenstar_crm.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infodart.kenstar_crm.entity.Attendance;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
	List<Attendance> findByEmployeeIdAndAttendanceDate(Long employeeId, LocalDate date);

	List<Attendance> findByEmployeeIdAndAttendanceDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);

}
