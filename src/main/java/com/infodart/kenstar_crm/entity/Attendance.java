package com.infodart.kenstar_crm.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.infodart.kenstar_crm.enums.AttendanceStatus;
import com.infodart.kenstar_crm.enums.AttendanceType;
import com.infodart.kenstar_crm.enums.DayName;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "attendance")
public class Attendance extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "User ID is required")
	private Long userId;

	@NotNull(message = "Date is required")
	private LocalDate date;

	private LocalTime checkInTime;

	private LocalTime checkOutTime;

	private Double workingHours;

	@Enumerated(EnumType.STRING)
	private AttendanceType attendanceType;

	@Enumerated(EnumType.STRING)
	private DayName dayName;

	@Enumerated(EnumType.STRING)
	private AttendanceStatus attendanceStatus;

	private Boolean isApproved;

	private int month;  // Field for the month
    private int year;   // Field for the year
    
    
	public Attendance() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long attendanceId) {
		this.id = attendanceId;
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

	public AttendanceStatus getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(AttendanceStatus status) {
		this.attendanceStatus = status;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
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

	
	
	
}
