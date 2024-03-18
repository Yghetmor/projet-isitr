package com.apprest.aventix.controller;

import com.apprest.aventix.payload.request.LoginRequest;
import com.apprest.aventix.payload.request.SignUpRequest;
import com.apprest.aventix.service.AuthService;
import com.apprest.aventix.service.OperationService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*")
@RestController
public class Controller {
    private final OperationService operationService;
    
    @Autowired
    private AuthService authService;

    @Autowired
    public Controller(OperationService operationService) {
        this.operationService = operationService;
    }
      
    @PostMapping("/signup")
    public ResponseEntity<?> registerEmployer(@Valid @RequestBody SignUpRequest signUpRequest){
    	return authService.registerEmployer(signUpRequest); 	
    }
    
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
    	return authService.authenticate(loginRequest);
    }
    
    @GetMapping("employer/home")
    @PreAuthorize("hasAuthority('ROLE_USER_EMPLOYER')")
    public String employerAccess() {
    	return "Employer Content";
    }
    
    @GetMapping("admin/home")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") 
    public String adminAccess() {
    	return "Admin Content";
    }
    
    
}
