package com.apprest.aventix.controller;

import com.apprest.aventix.model.Commande;
import com.apprest.aventix.payload.request.LoginRequest;
import com.apprest.aventix.payload.request.SignUpRequest;
import com.apprest.aventix.service.AuthService;
import com.apprest.aventix.service.CommandeService;
import com.apprest.aventix.service.OperationService;
import com.apprest.aventix.model.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*")
@RestController
public class Controller {
    private final OperationService operationService;
    
    // Appel des services 
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private CommandeService commandeService;

    @Autowired
    public Controller(OperationService operationService) {
        this.operationService = operationService;
    }

    @PutMapping("/operation")
    public ResponseEntity<?> processOperation(@RequestBody Operation operation) {
        return operationService.processOperation(operation);
    }
    
    // Partie authentication
      
    @PostMapping("/signup")
    public ResponseEntity<?> registerEmployer(@Valid @RequestBody SignUpRequest signUpRequest){
    	return authService.registerEmployer(signUpRequest); 	
    }
    
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
    	return authService.authenticate(loginRequest);
    }
    
    // Partie commande 
    
    @GetMapping("/commande")
    public ResponseEntity<?> findAllCommands(){
    	return commandeService.findAll();

    }
    
	@GetMapping("/commande/{id}")
	public ResponseEntity<?> findCommandById(@PathVariable long id) {
		return commandeService.findById(id);

	}
	
	@PostMapping("/commande")
	public ResponseEntity<?> addCommand(@RequestBody Commande commande){
		return commandeService.addOne(commande);	
	}
	
	@PostMapping("/commande/{id}")
	public ResponseEntity<?> modifyCommand(@PathVariable int id, @RequestBody Commande commande){
		return commandeService.editOne(id, commande);
	}
	
    @GetMapping("/employer/{employerId}")
    public ResponseEntity<?> findCommandsByEmployerId(@PathVariable Long employerId) {
        return commandeService.findByEmployerId(employerId);
    }
    
    // Nour
    
    @PutMapping("/confirm/{numCommande}")
    public ResponseEntity<?> confirmCommandeByAdmin(@PathVariable long numCommande) {
    	return commandeService.confirmByAdmin(numCommande);
    }

    @PutMapping("/livrer/{numCommande}") public ResponseEntity<Commande>
    confirmCommandeByNumberByEmployer(@PathVariable long numCommande) { return
    		commandeService.confirmByNumber(numCommande); }

    @PutMapping("/annuler/{numCommande}") public ResponseEntity<Commande>
    deleteCommande(@PathVariable long numCommande) { return
    		commandeService.deleteOne(numCommande); }
		 
		
   // Test  
    
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
