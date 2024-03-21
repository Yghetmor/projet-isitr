package com.apprest.aventix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apprest.aventix.model.Commande;

import java.util.List;
	
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
	//SELECT c FROM Commande c WHERE c.utilisateur.id = :employerId
	List<Commande> findByUtilisateurId(long employerId);
	
		
		


}

