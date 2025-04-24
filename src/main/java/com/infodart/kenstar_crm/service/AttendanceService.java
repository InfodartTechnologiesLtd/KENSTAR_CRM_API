package com.infodart.kenstar_crm.service;

import java.time.LocalDate;
import java.util.List;

import com.infodart.kenstar_crm.dto.AttendanceDto;
import com.infodart.kenstar_crm.dto.AttendanceSummaryDto;
import com.infodart.kenstar_crm.entity.Attendance;

public interface AttendanceService {

//	public Attendance markAttendanceIn(Long employeeId);
//
//	public Attendance markAttendanceOut(Long employeeId);
//
//	public List<Attendance> getLast7DaysAttendance(Long employeeId);
	
	
	
	AttendanceDto markAttendance(Long userId, AttendanceDto dto);

    //List<AttendanceDto> getAttendanceByUser(Long userId);
    public List<AttendanceDto> getAllAttendanceByUser(Long userId);
    public AttendanceDto getAttendanceById(Long id);
    public AttendanceDto updateAttendance(Long id, AttendanceDto dto);
    public void deleteAttendance(Long id);
    public AttendanceSummaryDto getAttendanceSummary(Long userId);
    public AttendanceDto approveAttendance(Long id);
    public AttendanceDto rejectAttendance(Long id);
    
    
    
    List<AttendanceDto> getAllAttendance();

    AttendanceDto getAttendanceByDate(Long userId, LocalDate date);

    AttendanceSummaryDto getSummary(Long userId, int month, int year);

     

    List<AttendanceDto> getPendingForApproval();
}
