package com.apprest.aventix.controller;

import com.apprest.aventix.service.OperationService;
import com.apprest.aventix.model.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    private final OperationService operationService;

    @Autowired
    public Controller(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/operation")
    public ResponseEntity<?> processOperation(@RequestBody Operation operation) {
        if (operationService.processOperation(operation)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
