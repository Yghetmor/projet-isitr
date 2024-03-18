package com.apprest.aventix.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.UUID;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.apprest.aventix.TestSetUpConfig;

import com.apprest.aventix.model.ERole;

import com.apprest.aventix.model.Role;
import com.apprest.aventix.payload.request.LoginRequest;
import com.apprest.aventix.payload.request.SignUpRequest;
import com.apprest.aventix.payload.response.JwtResponse;
import com.apprest.aventix.payload.response.MessageResponse;
import com.apprest.aventix.repository.AccountRepository;
import com.apprest.aventix.repository.AdminRepository;
import com.apprest.aventix.repository.EmployerRepository;
import com.apprest.aventix.repository.RoleRepository;
import com.apprest.aventix.service.AuthService;




@SpringBootTest
@ActiveProfiles("test")
@Import(TestSetUpConfig.class)
public class AuthServiceTest {
	
	@Autowired
	AuthService authService;
		
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	SignUpRequest signUpRequest;
	LoginRequest loginRequest;
	Role employerRole;
	
	private final String expectedToken = "mockJwtToken";
	
	

	
	/***********************************************************************************************************/
	/******************************************** TEST AUTH SERVICE ********************************************/
	/***********************************************************************************************************/
	
    @BeforeEach
    void setUp() {  	
    	employerRole = roleRepository.findByRoleType(ERole.ROLE_USER_EMPLOYER).get();
        signUpRequest = new SignUpRequest("a@b.com", "bbbbbb", 123456L, employerRole);
        loginRequest = new LoginRequest("e@f.com", "employer");      
    }
		
	/******************************************** TEST REGISTER ********************************************/
	
	// First scenario : Email already exists     
    @Test
    void whenEmailExists_thenBadRequest() {

    	String existingEmail = "admin@admin.com";
    	signUpRequest.setEmail(existingEmail);  
    	
    	ResponseEntity<?> response = authService.registerEmployer(signUpRequest);
        assertEquals("Error: Email déjà utilisé!",((MessageResponse)response.getBody()).getMessage());
    }
    
    // Second scenario : N°SIREN already exists / Email doesn't exist
    @Test
    void whenSirenExists_thenBadRequest() {
    	String uniqueEmail;
    	do {
            uniqueEmail = UUID.randomUUID().toString() + "@test.com";
        } while (accountRepository.existsByEmail(uniqueEmail));
    	signUpRequest.setEmail(uniqueEmail);
        Long existingSiren = 1111L;
        signUpRequest.setNoSiren(existingSiren);

        ResponseEntity<?> response = authService.registerEmployer(signUpRequest);
        assertEquals("Error: N°SIREN déjà utilisé!", ((MessageResponse) response.getBody()).getMessage());
    }
    
    @Test
    void whenSuccessfulRegistration_thenOk() {
        ResponseEntity<?> response = authService.registerEmployer(signUpRequest);
        assertEquals("Employer registered successfully!", ((MessageResponse) response.getBody()).getMessage());
    }
    
	/******************************************** TEST LOGIN ********************************************/
    
    @Test
    void whenAuthenticateWithInvalidPassword_thenFail() {
    	String wrongPassword = "wrongpassword";
    	loginRequest.setPassword(wrongPassword);

        ResponseEntity<?> response = authService.authenticate(loginRequest);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertTrue(response.getBody() instanceof MessageResponse);
        assertEquals("Les identifications sont erronées", ((MessageResponse) response.getBody()).getMessage());
    }
    
    @Test
    void whenAuthenticateWithInvalidEmail_thenFail() {
    	String wrongEmail= "wrong@email.com";
    	loginRequest.setEmail(wrongEmail);

        ResponseEntity<?> response = authService.authenticate(loginRequest);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertTrue(response.getBody() instanceof MessageResponse);
        assertEquals("Les identifications sont erronées", ((MessageResponse) response.getBody()).getMessage());
    }
    
    @Test
    void authenticateWithValidCredentials_thenSucceed() {
 
        ResponseEntity<?> response = authService.authenticate(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof JwtResponse);
        JwtResponse jwtResponse = (JwtResponse) response.getBody();
        assertNotNull(jwtResponse.getAccessToken());
        assertEquals(loginRequest.getEmail(), jwtResponse.getUsername());
    }
	
	/******************************************** TEST REPOSITORIES ********************************************/
	
    @Test
    public void whenFindByRoleType_thenReturnRole() {
        Optional<Role> foundRole = roleRepository.findByRoleType(ERole.ROLE_ADMIN);
        assertTrue(foundRole.isPresent());
        assertEquals(ERole.ROLE_ADMIN, foundRole.get().getRoleType());
    }
    
    @Test
    public void whenNotFindByRoleType_thenReturnRole() {
        Optional<Role> foundRole = roleRepository.findByRoleType(ERole.ROLE_MODERATOR);
        assertFalse(foundRole.isPresent());
    }
    
    @Test
    public void whenExistsByEmail_thenReturnTrue() {
    	
        boolean exists = accountRepository.existsByEmail("admin@admin.com");
        assertTrue(exists, "Email found");
    }
    
    @Test
    public void whenNotExistsByEmail_thenReturnFalse() {
    	
        boolean exists = accountRepository.existsByEmail("lealinh.liebard@gmail.com");
        assertFalse(exists, "Email not found");
    }
	

	

}
