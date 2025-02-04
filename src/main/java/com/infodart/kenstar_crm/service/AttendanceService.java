package com.infodart.kenstar_crm.service;

import java.util.List;

import com.infodart.kenstar_crm.entity.Attendance;

public interface AttendanceService {

	public Attendance markAttendanceIn(Long employeeId);

	public Attendance markAttendanceOut(Long employeeId);

	public List<Attendance> getLast7DaysAttendance(Long employeeId);
}
