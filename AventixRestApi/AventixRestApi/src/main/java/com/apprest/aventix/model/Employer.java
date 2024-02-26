package com.apprest.aventix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "employers")
public class Employer extends User{
	private Long noSiren;  

}
