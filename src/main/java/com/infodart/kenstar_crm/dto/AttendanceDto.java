package com.infodart.kenstar_crm.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceDto {

	private Long id;
	private Long employeeId;
	private LocalDate attendanceDate;
	private LocalDateTime inTime;
	private LocalDateTime outTime;

	public AttendanceDto() {
		super();
	}

	public AttendanceDto(Long id, Long employeeId, LocalDate attendanceDate, LocalDateTime inTime,
			LocalDateTime outTime) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.attendanceDate = attendanceDate;
		this.inTime = inTime;
		this.outTime = outTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public LocalDateTime getInTime() {
		return inTime;
	}

	public void setInTime(LocalDateTime inTime) {
		this.inTime = inTime;
	}

	public LocalDateTime getOutTime() {
		return outTime;
	}

	public void setOutTime(LocalDateTime outTime) {
		this.outTime = outTime;
	}

	@Override
	public String toString() {
		return "AttendanceDto [id=" + id + ", employeeId=" + employeeId + ", attendanceDate=" + attendanceDate
				+ ", inTime=" + inTime + ", outTime=" + outTime + "]";
	}

}
