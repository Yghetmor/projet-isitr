package com.apprest.aventix.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "accounts", 
	uniqueConstraints = {
		@UniqueConstraint(columnNames = "email")
	})
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String email;
	
	@NonNull
	private String password;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	



	public void setRole(Role role) {
		this.role = role;
	}
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
	
	
	

	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Role getRole() {
		return role;
	}


/*
	public Account() {
		
	}

*/	

	public Account(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	


}
