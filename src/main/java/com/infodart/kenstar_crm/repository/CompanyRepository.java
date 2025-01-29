package com.infodart.kenstar_crm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infodart.kenstar_crm.entity.Company;
import com.infodart.kenstar_crm.entity.User;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long>{
	
	public List<Company> findAllByCompanyCode(String companyCode);
	
	public List<Company> findAllByCompanyName(String companyName);
	
	public Company findAllById(int companyId);

}
