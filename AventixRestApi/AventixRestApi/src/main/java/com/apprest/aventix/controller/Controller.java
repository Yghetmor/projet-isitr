package com.apprest.aventix.controller;

import com.apprest.aventix.service.OperationService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final OperationService operationService;

    @Autowired
    public Controller(OperationService operationService) {
        this.operationService = operationService;
    }
    
    
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
    	
    }
}
