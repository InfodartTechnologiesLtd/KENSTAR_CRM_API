package com.infodart.kenstar_crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infodart.kenstar_crm.entity.Role;

  

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
  //Optional<Role> findByName(ERole name); 
  
  Role findByName(String name);
}