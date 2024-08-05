package com.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.config.AppConstants;
import com.blog.entities.Role;
import com.blog.repositories.RoleRepo;




@SpringBootApplication
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BlogAppApisApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	

	// this modelmapper is created in configuration class
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("this is your password : " + this.passwordEncoder.encode("xyz"));
		
		try {
			Role role = new Role();
			role.setId(AppConstants.ROLE_ADMIN);
			role.setName("ROLE_ADMIN");
			
			Role role1 = new Role();
			role1.setId(AppConstants.ROLE_NORMAL);
			role1.setName("ROLE_NORMAL");
			
			
			List<Role> roles = List.of(role, role1);
			List<Role> result = this.roleRepo.saveAll(roles);
			
			result.forEach( r ->  System.out.println(r.getName()));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
