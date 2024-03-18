package com.apprest.aventix.model;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "RECLAMATION")
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUMERO_RECLAMATION")
    private long reclamationNumber;

    @Column(name = "DATE_RECLAMATION")
    private Date dateReclamation;

    @Column(name = "MESSAGE_RECLAMATION", length = 300)
    private String messageReclamation;

    @ManyToOne
    @JoinColumn(name = "NUMERO_COMMANDE", referencedColumnName = "NUMERO_COMMANDE")
    private Commande commande;
    
    

	public Reclamation() {

	}

	public Reclamation(Date dateReclamation, String messageReclamation, Commande commande) {
		this.dateReclamation = dateReclamation;
		this.messageReclamation = messageReclamation;
		this.commande = commande;
	}

	public long getReclamationNumber() {
		return reclamationNumber;
	}

	public void setReclamationNumber(long reclamationNumber) {
		this.reclamationNumber = reclamationNumber;
	}

	public Date getDateReclamation() {
		return dateReclamation;
	}

	public void setDateReclamation(Date dateReclamation) {
		this.dateReclamation = dateReclamation;
	}

	public String getMessageReclamation() {
		return messageReclamation;
	}

	public void setMessageReclamation(String messageReclamation) {
		this.messageReclamation = messageReclamation;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

    // Getter, setter, etc.
    
    
}