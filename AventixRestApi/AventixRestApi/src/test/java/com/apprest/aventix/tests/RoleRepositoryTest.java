package com.apprest.aventix.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.apprest.aventix.AventixRestApiApplication;
import com.apprest.aventix.model.ERole;
import com.apprest.aventix.model.Role;
import com.apprest.aventix.repository.RoleRepository;
import com.apprest.aventix.security.JwtUtils;
import com.apprest.aventix.security.SecurityConfig;
import com.apprest.aventix.service.CustomAccountDetailsService;

import jakarta.transaction.Transactional;


@SpringBootApplication
public class RoleRepositoryTest {
	
	
	@Mock
	private RoleRepository roleRepository;
	

	
	
	  @Before
	  public void setUp() {
	  
	  
	  
	  
	  }
	 
	 
	
	@Test
    public void findByRoleType_WhenRoleExists() {
        // Test finding an existing role
        Optional<Role> foundRole = roleRepository.findByRoleType(ERole.ROLE_USER_EMPLOYER);
//        foundRole.toString();
//        System.out.println(foundRole.get().getRoleType());
//        assertThat(foundRole.get().getRoleType()).isEqualTo(ERole.ROLE_USER_EMPLOYER);
    }

//    @Test
//    public void findByRoleType_WhenRoleDoesNotExist() {
//        // Test finding a non-existing role
//        Optional<Role> foundRole = roleRepository.findByRoleType(ERole.ROLE_ADMIN);
//        assertThat(foundRole).isNotPresent();
//    }
	

}
