package com.apprest.aventix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apprest.aventix.model.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long>{
	
	boolean existsByNoSiren(Long noSiren);
	
    @Query("SELECT e FROM Employer e WHERE e.account.email = :email")
    Optional<Employer> findEmployerByAccountEmail(String email);

}
