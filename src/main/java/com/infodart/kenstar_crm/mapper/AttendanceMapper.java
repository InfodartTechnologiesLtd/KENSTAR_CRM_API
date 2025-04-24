package com.infodart.kenstar_crm.mapper;

import org.springframework.stereotype.Component;

import com.infodart.kenstar_crm.dto.AttendanceDto;
import com.infodart.kenstar_crm.dto.AttendanceSummaryDto;
import com.infodart.kenstar_crm.entity.Attendance;
import com.infodart.kenstar_crm.entity.User;

@Component
public class AttendanceMapper {

	
	public static AttendanceDto toDto(Attendance attendance) {
        AttendanceDto dto = new AttendanceDto();
        dto.setId(attendance.getId());
        dto.setUserId(attendance.getUserId());
        dto.setDate(attendance.getDate());
        dto.setCheckInTime(attendance.getCheckInTime());
        dto.setCheckOutTime(attendance.getCheckOutTime());
        dto.setWorkingHours(attendance.getWorkingHours());
        dto.setAttendanceType(attendance.getAttendanceType());
        dto.setDayName(attendance.getDayName());
        dto.setIsApproved(attendance.getIsApproved());
        dto.setAttendanceStatus(attendance.getAttendanceStatus());
        return dto;
    }

    public static Attendance toEntity(AttendanceDto dto, User user) {
        Attendance attendance = new Attendance();
        attendance.setUserId(dto.getUserId());
        attendance.setDate(dto.getDate());
        attendance.setCheckInTime(dto.getCheckInTime());
        attendance.setCheckOutTime(dto.getCheckOutTime());
        attendance.setWorkingHours(dto.getWorkingHours());
        attendance.setAttendanceType(dto.getAttendanceType());
        attendance.setDayName(dto.getDayName());
        attendance.setIsApproved(dto.getIsApproved());
        attendance.setAttendanceStatus(dto.getAttendanceStatus());
        return attendance;
    }

    public static AttendanceSummaryDto toSummaryDto(long present, long halfDay, long leave, long total) {
        AttendanceSummaryDto summary = new AttendanceSummaryDto();
        summary.setPresentDays(present);
        summary.setHalfDays(halfDay);
        summary.setLeaves(leave);
        summary.setTotalDays(total);
        return summary;
    }
}
