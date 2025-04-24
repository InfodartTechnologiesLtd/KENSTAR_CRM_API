package com.infodart.kenstar_crm.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infodart.kenstar_crm.entity.Leave;
import com.infodart.kenstar_crm.enums.LeaveStatus;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {

	 
	//public List<Leave> findByEmployeeId(Long employeeId);
	
	List<Leave> findByUserId(Long userId);
    List<Leave> findByLeaveStatus(LeaveStatus status);
    List<Leave> findByUserIdAndLeaveStatus(Long userId, LeaveStatus status); 
    
    
    
 // Find all leaves by a specific user
    List<Leave> findAllByUserId(Long userId);

    // Find all leaves by status (e.g., PENDING)
    List<Leave> findAllByLeaveStatus(LeaveStatus leaveStatus);

    // Optional: Filter by user and status
    List<Leave> findAllByUserIdAndLeaveStatus(Long userId, LeaveStatus leaveStatus);

    // Optional: Filter by date range
    List<Leave> findAllByUserIdAndStartDateBetween(Long userId, LocalDateTime start, LocalDateTime end);

    // Optional: Find leave requests overlapping a given date (for validation)
    @Query("SELECT l FROM Leave l WHERE l.userId  = :userId AND " +
           "(l.startDate <= :endDate AND l.endDate >= :startDate)")
    List<Leave> findOverlappingLeaves(@Param("userId") Long userId,
                                      @Param("startDate") LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate);


    
    
}
