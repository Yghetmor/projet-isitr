package com.aventixcommande.commande.ripository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aventixcommande.commande.entity.Facture;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
  
	
	
}