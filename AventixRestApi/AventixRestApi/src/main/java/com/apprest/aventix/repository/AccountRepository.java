package com.apprest.aventix.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apprest.aventix.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	boolean existsByEmail(String email);
	Optional<Account> findByEmail(String email);

}
