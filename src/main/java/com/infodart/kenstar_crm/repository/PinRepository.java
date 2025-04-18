package com.infodart.kenstar_crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infodart.kenstar_crm.entity.Company;
import com.infodart.kenstar_crm.entity.Pin;
import com.infodart.kenstar_crm.entity.User;

@Repository
public interface PinRepository extends CrudRepository<Pin, Long>{
	
	public List<Pin> findAllByPinCode(String pincode);
	
	public Pin findByPinCode(String pincode);
	
	boolean existsByUserId(Long userId);
	
	//public Pin findByUserId(Long userId);
	Optional<Pin> findByUser_Id(Long userId);
}
