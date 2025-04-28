package com.infodart.kenstar_crm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
 

@Entity
@Table(name = "leave_balances")
public class LeaveBalance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false, unique = true)
//    private User user;
    
    @NotNull(message = "User ID is required")
	private Long userId;

    private int totalLeaves = 0;      // All leaves accumulated
    private int usedLeaves = 0;       // Leaves used
    private int remainingLeaves = 0;  // totalLeaves - usedLeaves
    @Column(name = "expired_leaves")
    private int expiredLeaves;
    
    public LeaveBalance() {}
    
 // Param constructor
    public LeaveBalance(Long userId, int totalLeaves, int usedLeaves, int remainingLeaves, int expiredLeaves) {
        this.userId = userId;
        this.totalLeaves = totalLeaves;
        this.usedLeaves = usedLeaves;
        this.remainingLeaves = remainingLeaves;
        this.expiredLeaves = expiredLeaves;
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
