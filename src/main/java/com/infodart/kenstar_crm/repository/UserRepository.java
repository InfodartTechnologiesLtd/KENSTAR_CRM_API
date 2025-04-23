package com.infodart.kenstar_crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infodart.kenstar_crm.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// public List<User> findAllByUsername(String username);

	//public List<User> findAllByEmail(String email);

//	public List<User> findAllByMobilenumber(String mobilenumber);

	@Query("SELECT u FROM User u WHERE u.username = :username")
	Optional<User> findByUsername(@Param("username") String username);
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	Optional<User> findByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.mobilenumber = :mobilenumber")
	Optional<User> findByMobilenumber(@Param("mobilenumber") String mobilenumber);

	 
	//boolean existsByUsername(String username);

//	@Query(value = "SELECT id FROM userdetail WHERE username = :username LIMIT 1", nativeQuery = true)
//	Long findIdByUsername(@Param("username") String username);
//	boolean existsByEmail(String email);

//	boolean existsByMobilenumber(String mobilenumber);

}
