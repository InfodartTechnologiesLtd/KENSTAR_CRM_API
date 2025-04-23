package com.infodart.kenstar_crm.dto;

public class UserDto {

	private Long id;

	private String username;

	private String email;

	private String password;

	private String mobilenumber;

	private String  role;

	private String createdBy;
	private String updatedBy;

	private int isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String  getRole() {
		return role;
	}

	public void setRole(String  role) {
		this.role = role;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", mobilenumber=" + mobilenumber + ", role=" + role + ", createdBy=" + createdBy + ", updatedBy="
				+ updatedBy + ", isActive=" + isActive + "]";
	}

	
	
}
