package com.apprest.aventix.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;
import java.time.Instant;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apprest.aventix.model.Account;
import com.apprest.aventix.model.Commande;
import com.apprest.aventix.model.ERole;
import com.apprest.aventix.model.Employer;
import com.apprest.aventix.model.Role;
import com.apprest.aventix.repository.CommandeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Service
public class CommandeService {
	
	@Autowired
	private CommandeRepository commandeRepository;
	
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<List<Commande>>(commandeRepository.findAll(), HttpStatus.OK);		
	}
	
	public ResponseEntity<?> findById(long id) {
		Optional<Commande> optionCond = commandeRepository.findById(id);
		return optionCond.isPresent() ? new ResponseEntity<Commande>(optionCond.get(), HttpStatus.OK)
				: new ResponseEntity<Commande>(HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<?> addOne(Commande commande){
		commandeRepository.save(commande);
		return new ResponseEntity<Commande>(commande, HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> editOne(long id, Commande commande){
		
		// to do 
		Commande c = commandeRepository.findById(id).get();
		
		c.setDateCommande(commande.getDateCommande());
		c.setNbCarte(commande.getNbCarte());
		c.setNumeroCommande(commande.getNumeroCommande());
		c.setReclamations(commande.getReclamations());
		c.setStatut(commande.getStatut());
		c.setTauxParticipation(commande.getTauxParticipation());
		c.setUtilisateur(commande.getUtilisateur());
		
		commandeRepository.save(c);

		return new ResponseEntity<Commande>(c,HttpStatus.CREATED);	
	}
	
    public ResponseEntity<?> findByEmployerId(long employerId) {
        List<Commande> commandes = commandeRepository.findByUtilisateurId(employerId);
        if (commandes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }
    
    public ResponseEntity<Commande> deleteOne(long numCommande) {
        Commande c1 = this.commandeRepository.findById(numCommande).orElseThrow();
        Timestamp dateOrigine = c1.getDateCommande();
        Instant instant = dateOrigine.toInstant();
        LocalDateTime localDateTimeToCompare = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDateTime currentDateTime = LocalDateTime.now();
        long difference = ChronoUnit.DAYS.between(localDateTimeToCompare, currentDateTime);
        if (difference <= 3) {
            c1.setStatut("Annuler");
            commandeRepository.save(c1);
            return new ResponseEntity<>(c1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    public ResponseEntity<Commande> confirmByNumber(long numCommande) {
        Commande c1 = this.commandeRepository.findById(numCommande).orElseThrow();
        if (c1.getStatut().equals("Confirmer")) {
            c1.setStatut("Livrer");
            commandeRepository.save(c1);
            return new ResponseEntity<>(c1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    
    
    
    public ResponseEntity<Commande> confirmByAdmin(long numCommande) {
        Commande c1 = this.commandeRepository.findById(numCommande).orElseThrow();
        if (c1.getStatut().equals("En cours")) {
            c1.setStatut("Confirmer");
            commandeRepository.save(c1);
            return new ResponseEntity<>(c1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
	
	
	
	
	
	
	
//    @PersistenceContext
//    private EntityManager entityManager;
//	
//    //methode qui permet de recuperer tout les instance commande dans notre BDD
//	public Iterable<Commande> getCommandeList(){
//		//recuperer les commande dans un iterable grace la mathode findall defini par defaut dans l'interface
//		 Iterable<Commande> commandes = this.commandeRepository.findAll();
//		 //deproxifier l'entité lié a la commande  et initialisé l'utilisateur de chaque commande pour recuperer les info's
//		 commandes.forEach(commande -> commande.getUtilisateur().getId());
//		 return commandes;
//		
//		
//	}
//	
//	
//	//methode qui permet de creer une commande et l'enregistrer dans la BDD grace au ripository
//	public void createCommande(Commande c) {
//		Timestamp dateJour = new Timestamp(new Date().getTime());
//		c.setDateCommande(dateJour);
//		c.setStatut("En cours");
//		
//		Role userRole = new Role(ERole.ROLE_USER_EMPLOYER);
//		Account account = new Account("employeur1@rasengan.com", "motdepasse1");
//		account.setRole(userRole);
//		account.setId(1L);
//		Employer employer = new Employer(123456789L, account);
//		employer.setId(1L);
//		
//		c.setUtilisateur(employer);
//
//		
//		commandeRepository.save(c);
//	}
//
//	
//	
//	//methode pour confirmer une commande coté administrateur
//	public Commande confirmCommandeAdmin(long numCommande) {
//		
//		Commande c1 = this.commandeRepository.findById(numCommande).orElseThrow();
//		if(c1.getStatut().equals("En cours")) {
//			c1.setStatut("Confirmer");
//			commandeRepository.save(c1);
//		}
//		return c1;
//		
//	}
//	
//	//methode pour confirmer la bonne reception d'une commande
//	public Commande getCommandeByNumber(long numCommande) {
//		
//		Commande c1 = this.commandeRepository.findById(numCommande).orElseThrow();
//		if(c1.getStatut().equals("Confirmer")) {
//			c1.setStatut("Livrer");
//			commandeRepository.save(c1);
//		}
//		return c1;
//		
//	}
//	
//	//methode pour annule une commande si delai -3jours
//	public Commande annuleCommandeService(long numCommande) {
//		
//		//recuperer a partir de la base de données une commande
//		Commande c1 = this.commandeRepository.findById(numCommande).orElseThrow();
//		// Récupérer la date de la commande
//		Timestamp dateOrigine = c1.getDateCommande();
//		Instant instant = dateOrigine.toInstant();
//		LocalDateTime localDateTimeToCompare = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//
//		// Récupérer la date et l'heure actuelles
//		LocalDateTime currentDateTime = LocalDateTime.now();
//
//		// La différence entre les deux dates et heures
//		long difference = ChronoUnit.DAYS.between(localDateTimeToCompare, currentDateTime);
//
//		// Si la différence est de trois jours ou moins, annuler
//		if (difference <= 3) {
//		    c1.setStatut("Annuler");
//		}
//		commandeRepository.save(c1);
//		
//		return c1;
//		
//	}
//
////methode pour recuperer les commandes d'un client donnée 
//	public List<Commande> getCommandesForClient(long idClient) {
//	    String jpql = "SELECT c FROM Commande c JOIN c.utilisateur e WHERE e.id = :employerId";
//	    List<Commande> commandes = entityManager.createQuery(jpql, Commande.class)
//	            .setParameter("employerId", idClient)
//	            .getResultList();
//	    System.out.println(commandes);
//	    return commandes;
//	}

}
