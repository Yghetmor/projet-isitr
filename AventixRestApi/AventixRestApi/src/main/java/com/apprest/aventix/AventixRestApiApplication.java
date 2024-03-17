package com.apprest.aventix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.apprest.aventix.model.Account;
import com.apprest.aventix.model.Admin;
import com.apprest.aventix.model.ERole;
import com.apprest.aventix.model.Employer;
import com.apprest.aventix.model.Role;
import com.apprest.aventix.repository.AccountRepository;
import com.apprest.aventix.repository.AdminRepository;
import com.apprest.aventix.repository.EmployerRepository;
import com.apprest.aventix.repository.RoleRepository;

@SpringBootApplication
public class AventixRestApiApplication implements CommandLineRunner{
	@Autowired
	EmployerRepository employerRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(AventixRestApiApplication.class, args);		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Role r1 = new Role(ERole.ROLE_USER_EMPLOYER);
		Role r2 = new Role(ERole.ROLE_MODERATOR);
		Role r3 = new Role(ERole.ROLE_ADMIN);
		roleRepository.save(r1);
		roleRepository.save(r2);
		roleRepository.save(r3);
		
		Account adminAccount = new Account("admin@admin.com",passwordEncoder.encode("adminadmin"));
		adminAccount.setRole(r3);
		Account employerAccount = new Account("e@f.com", passwordEncoder.encode("employer"));
		employerAccount.setRole(r1);
		
		accountRepository.save(adminAccount);
		accountRepository.save(employerAccount);
		
		Employer employer = new Employer((long)1111, employerAccount);
		employerRepository.save(employer);
		
		Admin admin = new Admin(adminAccount);
		adminRepository.save(admin);
				
	}

}
