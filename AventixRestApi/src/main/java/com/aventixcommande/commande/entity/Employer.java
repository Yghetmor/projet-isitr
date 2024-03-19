package com.aventixcommande.commande.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "employers")
@Data
@AllArgsConstructor
public class Employer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	private Long noSiren;  
	
	@OneToOne
	private Account account;
	
	
	
	public Long getNoSiren() {
		return noSiren;
	}



	public void setNoSiren(Long noSiren) {
		this.noSiren = noSiren;
	}



	public Account getAccount() {
		return account;
	}



	public void setAccount(Account account) {
		this.account = account;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	

	public Employer() {

		
	}



	public Employer(Long noSiren, Account account) {
		this.noSiren=noSiren;
		this.account=account;
	}

}
