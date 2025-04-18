package com.infodart.kenstar_crm.dto;

public class PinDto {

	private Integer id;
	private String pinCode;

	private String oldPinCode;

	private Long userId;
	private int companyId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getOldPinCode() {
		return oldPinCode;
	}

	public void setOldPinCode(String oldPinCode) {
		this.oldPinCode = oldPinCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

}
