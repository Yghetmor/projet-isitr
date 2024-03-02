package com.apprest.aventix.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apprest.aventix.model.Account;
import com.apprest.aventix.repository.AccountRepository;

@Service
public class CustomAccountDetailsService implements UserDetailsService{
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Account account = accountRepository.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException("User not found with email"));
		
		GrantedAuthority authority = new SimpleGrantedAuthority(account.getRole().getRoleType().name());
		
		// User constructor do not take only one authority
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);
			
		// Here is return User from org.springframework.security.core.userdetails
		return new User(account.getEmail(), account.getPassword(), authorities);
	}

}
