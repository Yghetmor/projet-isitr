package com.apprest.aventix.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.apprest.aventix.model.ERole;
import com.apprest.aventix.model.Employer;
import com.apprest.aventix.model.Role;
import com.apprest.aventix.model.Account;
import com.apprest.aventix.payload.request.LoginRequest;
import com.apprest.aventix.payload.request.SignUpRequest;
import com.apprest.aventix.payload.response.JwtResponse;
import com.apprest.aventix.payload.response.MessageResponse;
import com.apprest.aventix.repository.EmployerRepository;
import com.apprest.aventix.repository.RoleRepository;
import com.apprest.aventix.security.JwtUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import com.apprest.aventix.repository.AccountRepository;



@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	EmployerRepository employerRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtUtils jwtUtils;

	public ResponseEntity<?> registerEmployer(SignUpRequest signUpRequest){
		if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email déjà utilisé!"));
		}

		if (employerRepository.existsByNoSiren(signUpRequest.getNoSiren())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: N°SIREN déjà utilisé!"));

		}

		Account account = new Account(signUpRequest.getEmail(),
				passwordEncoder.encode(signUpRequest.getPassword()));

		Role employerRole = roleRepository.findByRoleType(ERole.ROLE_USER_EMPLOYER)
				.orElseThrow(() -> new RuntimeException("Error: Role not found."));

		account.setRole(employerRole);

		Employer employer = new Employer(signUpRequest.getNoSiren(),account);

		accountRepository.save(account);
		employerRepository.save(employer);

		return ResponseEntity.ok(new MessageResponse("Employer registered successfully!"));

	}


	public ResponseEntity<?> authenticate(LoginRequest loginRequest){

		try {
			UsernamePasswordAuthenticationToken upaToken = 
					new UsernamePasswordAuthenticationToken(
							loginRequest.getEmail(), 
							loginRequest.getPassword()
							);

			Authentication authentication = authenticationManager.authenticate(upaToken);

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwtToken = jwtUtils.generateJwtToken(authentication);

			User accountPrincipal = (User) authentication.getPrincipal();

			//maybe throw exception better than empty string ? 
			String role = accountPrincipal.getAuthorities().stream() .findFirst().map(item ->
			item.getAuthority()).orElse("");
			
			long idEmpl = employerRepository.findEmployerByAccountEmail(accountPrincipal.getUsername())
					.map(Employer::getId).orElse(0L);
			

			return ResponseEntity.ok(new JwtResponse( jwtToken,
					accountPrincipal.getUsername(), role,idEmpl));

		}catch (AuthenticationException e){
			e.getMessage();
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse(e.getMessage()));

		}

	}
	




}
