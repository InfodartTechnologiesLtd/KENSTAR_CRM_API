package com.infodart.kenstar_crm.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "holidays")
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private LocalDate date;

    private String description;

    private Boolean isNationalHoliday = true;
    
	public Holiday() {
		super();
	}

	public Holiday(  String name, LocalDate date, String description, Boolean isNationalHoliday) {
		super();
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
