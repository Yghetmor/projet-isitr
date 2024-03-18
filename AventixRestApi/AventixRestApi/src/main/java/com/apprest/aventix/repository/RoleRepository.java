package com.apprest.aventix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apprest.aventix.model.ERole;
import com.apprest.aventix.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByRoleType(ERole roleType);

}
