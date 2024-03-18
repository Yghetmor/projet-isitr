package com.apprest.aventix.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apprest.aventix.model.Commande;




	
	@Repository
	public interface CommandeRepository extends JpaRepository<Commande, Long> {
		
		


	}

