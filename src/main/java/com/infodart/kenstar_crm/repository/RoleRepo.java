package com.infodart.kenstar_crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infodart.kenstar_crm.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

	@Query("SELECT r FROM Role r WHERE r.name = :name")
	Optional<Role> findByName(@Param("name") String name);

	boolean existsByName(String name);
}