package com.aventixcommande.commande.ripository;
import com.aventixcommande.commande.entity.*;



import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
	
	


}
