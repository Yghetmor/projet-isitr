package com.apprest.aventix.model;

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
	
	public Employer(Long noSiren, Account account) {
		this.noSiren=noSiren;
		this.account=account;
	}

}
