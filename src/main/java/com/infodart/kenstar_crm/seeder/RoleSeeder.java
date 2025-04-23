package com.infodart.kenstar_crm.seeder;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.infodart.kenstar_crm.entity.ERole;
import com.infodart.kenstar_crm.repository.RoleRepo;
import com.infodart.kenstar_crm.entity.Role;
import jakarta.annotation.PostConstruct;

@Component
public class RoleSeeder {
	@Autowired
	private RoleRepo roleRepo;

	@PostConstruct
	public void seedRoles() {
		System.out.println(">>> Starting RoleSeeder..");
		try {
			for (ERole roleType : ERole.values()) {
				System.out.println(">>> Checking role: {}" + roleType.name());
				Optional<Role> optionalRole = roleRepo.findByName(roleType.name());
				if (optionalRole.isEmpty()) {
					Role role = new Role();
					role.setName(roleType.name());
					roleRepo.save(role);
					System.out.println("Inserted role: " + roleType.name());
				}
			}
		} catch (Exception e) {
			System.err.println("Error in RoleSeeder: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
