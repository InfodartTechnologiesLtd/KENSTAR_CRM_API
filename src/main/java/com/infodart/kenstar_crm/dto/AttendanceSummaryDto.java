package com.infodart.kenstar_crm.dto;

public class AttendanceSummaryDto {

	private long presentDays;
	private long halfDays;
	private long leaves;
	private long totalDays;

	public AttendanceSummaryDto() {
		super();
	}

	public AttendanceSummaryDto(long presentDays, long halfDays, long leaves, long totalDays) {
		super();
		this.presentDays = presentDays;
		this.halfDays = halfDays;
		this.leaves = leaves;
		this.totalDays = totalDays;
	}

	public long getPresentDays() {
		return presentDays;
	}

	public void setPresentDays(long presentDays) {
		this.presentDays = presentDays;
	}

	public long getHalfDays() {
		return halfDays;
	}

	public void setHalfDays(long halfDays) {
		this.halfDays = halfDays;
	}

	public long getLeaves() {
		return leaves;
	}

	public void setLeaves(long leaves) {
		this.leaves = leaves;
	}

	public long getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(long totalDays) {
		this.totalDays = totalDays;
	}

	@Override
	public String toString() {
		return "AttendanceSummaryDto [presentDays=" + presentDays + ", halfDays=" + halfDays + ", leaves=" + leaves
				+ ", totalDays=" + totalDays + "]";
	}
	
	
	
	

}
