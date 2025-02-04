package com.infodart.kenstar_crm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infodart.kenstar_crm.dto.LeaveDto;
import com.infodart.kenstar_crm.entity.Company;
import com.infodart.kenstar_crm.entity.Leave;

@Repository
public interface LeaveRepository extends CrudRepository<Leave, Long> {

	 
	public List<Leave> findByEmployeeId(Long employeeId);
}
