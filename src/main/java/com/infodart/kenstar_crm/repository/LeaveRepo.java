package com.infodart.kenstar_crm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infodart.kenstar_crm.dto.LeaveDto;
import com.infodart.kenstar_crm.entity.Company;
import com.infodart.kenstar_crm.entity.Leave;

@Repository
public interface LeaveRepo extends CrudRepository<Leave, Long> {

	public Leave findAllById(int employeeId);
}
