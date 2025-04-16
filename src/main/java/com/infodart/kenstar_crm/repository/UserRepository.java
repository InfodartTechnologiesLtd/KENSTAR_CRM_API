package com.infodart.kenstar_crm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infodart.kenstar_crm.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findAllByUsername(String username);

	public List<User> findAllByEmail(String email);

	public List<User> findAllByMobilenumber(String mobilenumber);
	
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	boolean existsByMobilenumber(String mobilenumber);

}
