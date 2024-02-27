package com.apprest.aventix.payload.request;

import com.apprest.aventix.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpRequest {

    private String email;
    private String password;
    private Long noSiren;
    private Role role;
    
	

}
