package com.infodart.kenstar_crm.dto;

import java.time.LocalDate;

public class HolidayDto {

	private Long id;
	private String name;
	private LocalDate date;
	private String description;
	private Boolean isNationalHoliday;

	public HolidayDto() {
		super();
	}

	public HolidayDto(Long id, String name, LocalDate date, String description, Boolean isNationalHoliday) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.description = description;
		this.isNationalHoliday = isNationalHoliday;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsNationalHoliday() {
		return isNationalHoliday;
	}

	public void setIsNationalHoliday(Boolean isNationalHoliday) {
		this.isNationalHoliday = isNationalHoliday;
	}

}
