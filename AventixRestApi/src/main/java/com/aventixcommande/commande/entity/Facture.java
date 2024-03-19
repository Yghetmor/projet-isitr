package com.aventixcommande.commande.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "factures")
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateEmission;

    private BigDecimal montant;

    private String statut;

    @OneToOne
    @JoinColumn(name = "commande_id", referencedColumnName = "id")
    private Commande commande;

    public Facture() {}

    public Facture(Date dateEmission, BigDecimal montant, String statut, Commande commande) {
    	
    	
        this.dateEmission = dateEmission; 
        this.montant = montant;
        this.statut = statut;
        this.commande = commande;
    }

    // Getters
    public Long getId() {
    	return id; 
    	}
    
    public void setId(Long id) {
    	this.id = id; 
    	}
    
    
    
    public Date getDateEmission() { 
    	return dateEmission; 
    	}
    
    public void setDateEmission(Date dateEmission) { 
    	this.dateEmission = dateEmission; 
    	}
    
    
    public BigDecimal getMontant() {
    	return montant; 
    	}
    
    public void setMontant(BigDecimal montant) {
    	this.montant = montant; 
    	}
    
    public String getStatut() { 
    	return statut; 
    }
    
    public void setStatut(String statut) { 
    	this.statut = statut; 
    	}
    
    public Commande getCommande() { 
    	return commande; 
    	}
    
    public void setCommande(Commande commande) { 
    	this.commande = commande; 
    	}
    
}