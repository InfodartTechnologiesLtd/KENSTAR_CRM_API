package com.infodart.kenstar_crm.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long employeeId;

	private LocalDate attendanceDate;

	private LocalDateTime inTime;

	private LocalDateTime outTime;

	private String createdBy;
	private String updatedBy;

	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date createdDateTime;

	@UpdateTimestamp
	@Column(name = "updated_at")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date updatedDateTime;

	public Attendance() {
		super();
	}

	public Attendance(Long employeeId) {

		this.employeeId = employeeId;
	}

	public Attendance(Long employeeId, LocalDate attendanceDate) {
		this(employeeId);
		this.attendanceDate = attendanceDate;
	}

	public Attendance(Long employeeId, LocalDate attendanceDate, LocalDateTime inTime) {

		this(employeeId, attendanceDate);
		this.inTime = inTime;
	}

	public Attendance(  Long employeeId, LocalDate attendanceDate, LocalDateTime inTime, LocalDateTime outTime) {
		this(  employeeId, attendanceDate, inTime);
		this.outTime = outTime;
	}

	public Attendance(  Long employeeId, LocalDate attendanceDate, LocalDateTime inTime, LocalDateTime outTime,
			String createdBy) {
		this(  employeeId, attendanceDate, inTime, outTime);
		this.createdBy = createdBy;
	}

	public Attendance(  Long employeeId, LocalDate attendanceDate, LocalDateTime inTime, LocalDateTime outTime,
			String createdBy, String updatedBy) {
		this(  employeeId, attendanceDate, inTime, outTime, createdBy);
		this.updatedBy = updatedBy;
	}

	public Attendance(  Long employeeId, LocalDate attendanceDate, LocalDateTime inTime, LocalDateTime outTime,
			String createdBy, String updatedBy, Date createdDateTime) {
		this(  employeeId, attendanceDate, inTime, outTime, createdBy, updatedBy);
		this.createdDateTime = createdDateTime;
	}

	public Attendance(  Long employeeId, LocalDate attendanceDate, LocalDateTime inTime, LocalDateTime outTime,
			String createdBy, String updatedBy, Date createdDateTime, Date updatedDateTime) {
		this(  employeeId, attendanceDate, inTime, outTime, createdBy, updatedBy, createdDateTime);
		this.updatedDateTime = updatedDateTime;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

}
