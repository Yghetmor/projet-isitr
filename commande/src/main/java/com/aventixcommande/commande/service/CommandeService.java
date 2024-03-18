package com.aventixcommande.commande.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Instant;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aventixcommande.commande.entity.Account;
import com.aventixcommande.commande.entity.Commande;
import com.aventixcommande.commande.entity.ERole;
import com.aventixcommande.commande.entity.Employer;
import com.aventixcommande.commande.entity.Role;
import com.aventixcommande.commande.ripository.CommandeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class CommandeService {
	
	//auto injection de la propriéte qui permet la communication avec la couche du dessus
	@Autowired
	private CommandeRepository commandeRepository;
	
	
    @PersistenceContext
    private EntityManager entityManager;
	
    //methode qui permet de recuperer tout les instance commande dans notre BDD
	public Iterable<Commande> getCommandeList(){
		//recuperer les commande dans un iterable grace la mathode findall defini par defaut dans l'interface
		 Iterable<Commande> commandes = this.commandeRepository.findAll();
		 //deproxifier l'entité lié a la commande  et initialisé l'utilisateur de chaque commande pour recuperer les info's
		 commandes.forEach(commande -> commande.getUtilisateur().getId());
		 return commandes;
		
		
	}
	
	
	//methode qui permet de creer une commande et l'enregistrer dans la BDD grace au ripository
	public void createCommande(Commande c) {
		Timestamp dateJour = new Timestamp(new Date().getTime());
		c.setDateCommande(dateJour);
		c.setStatut("En cours");
		
		Role userRole = new Role(1, ERole.ROLE_USER_EMPLOYER);
		Account account = new Account("employeur1@rasengan.com", "motdepasse1");
		account.setRole(userRole);
		account.setId(1L);
		Employer employer = new Employer(123456789L, account);
		employer.setId(1L);
		
		c.setUtilisateur(employer);

		
		commandeRepository.save(c);
	}

	
	
	//methode pour confirmer une commande coté administrateur
	public Commande confirmCommandeAdmin(long numCommande) {
		
		Commande c1 = this.commandeRepository.findById(numCommande).orElseThrow();
		if(c1.getStatut().equals("En cours")) {
			c1.setStatut("Confirmer");
			commandeRepository.save(c1);
		}
		return c1;
		
	}
	
	//methode pour confirmer la bonne reception d'une commande
	public Commande getCommandeByNumber(long numCommande) {
		
		Commande c1 = this.commandeRepository.findById(numCommande).orElseThrow();
		if(c1.getStatut().equals("Confirmer")) {
			c1.setStatut("Livrer");
			commandeRepository.save(c1);
		}
		return c1;
		
	}
	
	//methode pour annule une commande si delai -3jours
	public Commande annuleCommandeService(long numCommande) {
		
		//recuperer a partir de la base de données une commande
		Commande c1 = this.commandeRepository.findById(numCommande).orElseThrow();
		// Récupérer la date de la commande
		Timestamp dateOrigine = c1.getDateCommande();
		Instant instant = dateOrigine.toInstant();
		LocalDateTime localDateTimeToCompare = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

		// Récupérer la date et l'heure actuelles
		LocalDateTime currentDateTime = LocalDateTime.now();

		// La différence entre les deux dates et heures
		long difference = ChronoUnit.DAYS.between(localDateTimeToCompare, currentDateTime);

		// Si la différence est de trois jours ou moins, annuler
		if (difference <= 3) {
		    c1.setStatut("Annuler");
		}
		commandeRepository.save(c1);
		
		return c1;
		
	}

//methode pour recuperer les commandes d'un client donnée 
	public List<Commande> getCommandesForClient(long idClient) {
	    String jpql = "SELECT c FROM Commande c JOIN c.utilisateur e WHERE e.id = :employerId";
	    List<Commande> commandes = entityManager.createQuery(jpql, Commande.class)
	            .setParameter("employerId", idClient)
	            .getResultList();
	    System.out.println(commandes);
	    return commandes;
	}

}
