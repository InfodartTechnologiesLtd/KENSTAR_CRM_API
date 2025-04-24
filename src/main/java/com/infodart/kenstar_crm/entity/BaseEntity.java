package com.infodart.kenstar_crm.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public abstract class BaseEntity {

	@Column(nullable = false, updatable = false)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedDate;

    
//    @CreationTimestamp
//	@Column(updatable = false, name = "created_at")
//	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
//	private Date createdDateTime;
//
//	@UpdateTimestamp
//	@Column(name = "updated_at")
//	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
//	private Date updatedDateTime;

	
    private Long createdBy;

    private Long updatedBy;

    @PrePersist
    public void onCreate() {
        this.createdDate = LocalDateTime.now();  // Current DateTime when entity is created
        this.updatedDate = LocalDateTime.now();  // Set initial update time to created time
        this.createdBy = getCurrentUserId();  // Placeholder for user ID fetching logic
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedDate = LocalDateTime.now();  // Update time whenever entity is updated
        this.updatedBy = getCurrentUserId();  // Fetch updated user ID
    }

    private Long getCurrentUserId() {
        // Fetch the current logged-in user's ID (from Spring Security or session context)
        return 1L; // Placeholder, replace with actual logic
    }

    // Getters and Setters
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

}
