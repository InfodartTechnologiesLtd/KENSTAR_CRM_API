package com.infodart.kenstar_crm.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infodart.kenstar_crm.enums.AttendanceStatus;
import com.infodart.kenstar_crm.enums.AttendanceType;
import com.infodart.kenstar_crm.enums.DayName;

public class AttendanceDto {

	private Long id;
	private Long userId;
	private LocalDate date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime checkInTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime checkOutTime;
	private Double workingHours;
	private AttendanceType attendanceType;
	private DayName dayName;
	private Boolean isApproved;
	private AttendanceStatus attendanceStatus;

	private int month; // Field for the month
	private int year; // Field for the year

	public AttendanceDto() {
		super();
	}

	public AttendanceDto(Long id, Long userId, LocalDate date, LocalTime checkInTime, LocalTime checkOutTime,
			Double workingHours, AttendanceType attendanceType, DayName dayName, Boolean isApproved,
			AttendanceStatus attendanceStatus, int month, int year) {
		super();
		this.id = id;
		this.userId = userId;
		this.date = date;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.workingHours = workingHours;
		this.attendanceType = attendanceType;
		this.dayName = dayName;
		this.isApproved = isApproved;
		this.attendanceStatus = attendanceStatus;
		this.month = month;
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(LocalTime checkInTime) {
		this.checkInTime = checkInTime;
	}

	public LocalTime getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(LocalTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public Double getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(Double workingHours) {
		this.workingHours = workingHours;
	}

	public AttendanceType getAttendanceType() {
		return attendanceType;
	}

	public void setAttendanceType(AttendanceType attendanceType) {
		this.attendanceType = attendanceType;
	}

	public DayName getDayName() {
		return dayName;
	}

	public void setDayName(DayName dayName) {
		this.dayName = dayName;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public AttendanceStatus getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "AttendanceDto [id=" + id + ", userId=" + userId + ", date=" + date + ", checkInTime=" + checkInTime
				+ ", checkOutTime=" + checkOutTime + ", workingHours=" + workingHours + ", attendanceType="
				+ attendanceType + ", dayName=" + dayName + ", isApproved=" + isApproved + ", attendanceStatus="
				+ attendanceStatus + "]";
	}

}
