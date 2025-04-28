package com.infodart.kenstar_crm.dto;

public class LeaveBalanceDto {

	private Long id;
	private Long userId;

	private int totalLeaves; // All leaves accumulated
	private int usedLeaves; // Leaves used
	private int remainingLeaves;
	private int expiredLeaves;
	
	
	
	
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
	public int getTotalLeaves() {
		return totalLeaves;
	}
	public void setTotalLeaves(int totalLeaves) {
		this.totalLeaves = totalLeaves;
	}
	public int getUsedLeaves() {
		return usedLeaves;
	}
	public void setUsedLeaves(int usedLeaves) {
		this.usedLeaves = usedLeaves;
	}
	public int getRemainingLeaves() {
		return remainingLeaves;
	}
	public void setRemainingLeaves(int remainingLeaves) {
		this.remainingLeaves = remainingLeaves;
	}
	public int getExpiredLeaves() {
		return expiredLeaves;
	}
	public void setExpiredLeaves(int expiredLeaves) {
		this.expiredLeaves = expiredLeaves;
	}

}
