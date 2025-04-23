package com.infodart.kenstar_crm.dto;

public class CompanyDto {

	private Long id;

	//private Integer companyId;
	private String companyName;
	private String companyCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Integer getCompanyId() {
//		return companyId;
//	}
//
//	public void setCompanyId(Integer companyId) {
//		this.companyId = companyId;
//	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

}
