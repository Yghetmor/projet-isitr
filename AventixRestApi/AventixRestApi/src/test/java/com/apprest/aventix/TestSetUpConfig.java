package com.apprest.aventix;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.apprest.aventix.model.Account;
import com.apprest.aventix.model.Admin;
import com.apprest.aventix.model.ERole;
import com.apprest.aventix.model.Employer;
import com.apprest.aventix.model.Role;
import com.apprest.aventix.repository.AccountRepository;
import com.apprest.aventix.repository.AdminRepository;
import com.apprest.aventix.repository.EmployerRepository;
import com.apprest.aventix.repository.RoleRepository;

@TestConfiguration
@Profile("test")
public class TestSetUpConfig {
	
    @Bean
    public CommandLineRunner setupTestDatabase(
        EmployerRepository employerRepository,
        AdminRepository adminRepository,
        RoleRepository roleRepository,
        AccountRepository accountRepository,
        PasswordEncoder passwordEncoder) {
        
        return args -> {
    		Role r1 = new Role(ERole.ROLE_USER_EMPLOYER);
    		Role r2 = new Role(ERole.ROLE_ADMIN);
    		roleRepository.save(r1);
    		roleRepository.save(r2);

    		
    		Account adminAccount = new Account("admin@admin.com",passwordEncoder.encode("adminadmin"));
    		adminAccount.setRole(r2);
    		Account employerAccount = new Account("e@f.com", passwordEncoder.encode("employer"));
    		employerAccount.setRole(r1);
    		
    		accountRepository.save(adminAccount);
    		accountRepository.save(employerAccount);
    		
    		Employer employer = new Employer((long)1111, employerAccount);
    		employerRepository.save(employer);
    		
    		Admin admin = new Admin(adminAccount);
    		adminRepository.save(admin);
        };
    }

}
