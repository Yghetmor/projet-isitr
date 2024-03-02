package com.apprest.aventix.security;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class jwtUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(jwtUtils.class);
	
	@Value("${security.jwt.secret-key}")
	private String secretKey;
	
	@Value("${security.jwt.expiration-time}")
	private Long jwtExpiration;
	
	  private Key key() {
		    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
		  }
	
	public String generateJwtToken(Authentication authentication) {
		// ici on utilise le User par défaut de spring security
		// sinon on peut créer une classe AccountDetail implements UserDetails
		User accountPrincipal = (User) authentication.getPrincipal();
			
		return Jwts.builder().setSubject(accountPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
				.signWith(key(), SignatureAlgorithm.HS256)
				.compact();
	
	}
	


	


}
