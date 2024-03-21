package com.apprest.aventix.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountInfoResponse {
	  private String email;
	  private String role;

}
