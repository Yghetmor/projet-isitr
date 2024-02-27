package com.apprest.aventix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apprest.aventix.model.ERole;
import com.apprest.aventix.model.Employer;
import com.apprest.aventix.model.Role;
import com.apprest.aventix.model.Account;
import com.apprest.aventix.payload.request.SignUpRequest;
import com.apprest.aventix.payload.response.MessageResponse;
import com.apprest.aventix.repository.EmployerRepository;
import com.apprest.aventix.repository.RoleRepository;
import com.apprest.aventix.repository.AccountRepository;



@Service
public class AuthService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	EmployerRepository employerRepository;
	
	public ResponseEntity<?> registerEmployer(SignUpRequest signUpRequest){
		if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
		    return ResponseEntity
		        .badRequest()
		        .body(new MessageResponse("Error: Email is already in use!"));
		}
		
		if (employerRepository.existsByNoSiren(signUpRequest.getNoSiren())) {
		    return ResponseEntity
			        .badRequest()
			        .body(new MessageResponse("Error: N°SIREN is already in use!"));
			
		}
		
		Account account = new Account(signUpRequest.getEmail(),
				signUpRequest.getPassword());
		
		Role employerRole = roleRepository.findByRoleType(ERole.ROLE_USER_EMPLOYER)
				.orElseThrow(() -> new RuntimeException("Error: Role not found."));
		
		account.setRole(employerRole);
			
		Employer employer = new Employer(signUpRequest.getNoSiren(),account);
		
		accountRepository.save(account);
		employerRepository.save(employer);
		
		return ResponseEntity.ok(new MessageResponse("Employer registered successfully!"));
				
	}
	


}
