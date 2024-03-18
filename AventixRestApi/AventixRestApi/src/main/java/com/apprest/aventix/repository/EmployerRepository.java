package com.apprest.aventix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apprest.aventix.model.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long>{
	
	boolean existsByNoSiren(Long noSiren);

}
