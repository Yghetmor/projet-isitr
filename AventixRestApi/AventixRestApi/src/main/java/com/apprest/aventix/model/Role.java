package com.apprest.aventix.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "roles")
//@Data
//@NoArgsConstructor
//@RequiredArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private ERole roleType;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<Account> accounts;

	public Role() {
		
	}

// rajouter constructeur 2 args 



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public ERole getRoleType() {
		return roleType;
	}



	public void setRoleType(ERole roleType) {
		this.roleType = roleType;
	}



	public List<Account> getAccounts() {
		return accounts;
	}



	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

*/

	
}
