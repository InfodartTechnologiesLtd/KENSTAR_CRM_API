package com.infodart.kenstar_crm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infodart.kenstar_crm.entity.User;

@Repository
public interface AuthRepository extends CrudRepository<User, Long>{
	
	public List<User> findAllByUsername(String username);
	
	public User findAllById(int userId);
	

}
