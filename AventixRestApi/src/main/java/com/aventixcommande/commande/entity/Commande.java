package com.aventixcommande.commande.entity;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "COMMANDE")
@Validated
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUMERO_COMMANDE")
    private long numeroCommande;
    
    @Min(value = 1, message = "Le nombre de cartes doit être au moins 1.")
    @Max(value = 1500, message = "Le nombre de cartes ne peut pas dépasser 1500.")
    @Column(name = "NB_CARTE")
    private int nbCarte;

    @Min(value = 0, message = "Le taux de participation ne peut pas être inférieur à 0.")
    @Max(value = 100, message = "Le taux de participation ne peut pas dépasser 100.")
    @Column(name = "TAUX_PARTICIPATION")
    private int tauxParticipation;

    @Column(name = "DATE_COMMANDE")
    private Timestamp dateCommande;

    @Column(name = "STATUT", length = 20)
    private String statut;

    @ManyToOne
    @JoinColumn(name = "ID_EMPLOYER", referencedColumnName = "id")
    @JsonManagedReference
    private Employer utilisateur;

    @OneToMany(mappedBy = "commande")
    private List<Reclamation> reclamations;
    
    
    

	public Commande() {
		
		this.numeroCommande = 0;
		this.nbCarte = 0;
		this.tauxParticipation = 0;
		this.dateCommande = null;
		this.statut = "";

	}

	public Commande(int nbCarte, int tauxParticipation, Timestamp dateCommande, String statut, Employer utilisateur,
			List<Reclamation> reclamations) {
		this.nbCarte = nbCarte;
		this.tauxParticipation = tauxParticipation;
		this.dateCommande = dateCommande;
		this.statut = statut;
		this.utilisateur = utilisateur;
		this.reclamations = reclamations;
	}



	public long getNumeroCommande() {
		return numeroCommande;
	}

	public void setNumeroCommande(long numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	public int getNbCarte() {
		return nbCarte;
	}

	public void setNbCarte(int nbCarte) {
		this.nbCarte = nbCarte;
	}

	public int getTauxParticipation() {
		return tauxParticipation;
	}

	public void setTauxParticipation(int tauxParticipation) {
		this.tauxParticipation = tauxParticipation;
	}

	public Timestamp getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Timestamp dateCommande) {
		this.dateCommande = dateCommande;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Employer getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Employer utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Reclamation> getReclamations() {
		return reclamations;
	}

	public void setReclamations(List<Reclamation> reclamations) {
		this.reclamations = reclamations;
	}


    
    
    
}