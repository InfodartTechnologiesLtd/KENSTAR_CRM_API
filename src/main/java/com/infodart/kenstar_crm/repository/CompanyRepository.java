package com.infodart.kenstar_crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infodart.kenstar_crm.entity.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

	public List<Company> findAllByCompanyCode(String companyCode);

	public List<Company> findAllByCompanyName(String companyName);

	public Company findAllById(int companyId);

	// Optional: fetch all companies by user id (if needed in future)
	List<Company> findByUser_Id(Long userId);

	Optional<Company> findByCompanyCode(String companyCode);

	Optional<Company> findByCompanyName(String companyName);

	Optional<Company> findById(Integer companyId);

}
