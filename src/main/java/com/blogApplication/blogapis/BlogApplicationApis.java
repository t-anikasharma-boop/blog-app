package com.blogApplication.blogapis;

import com.blogApplication.blogapis.entities.Role;
import com.blogApplication.blogapis.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class BlogApplicationApis implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;
	public static void main(String[] args) {

		SpringApplication.run(BlogApplicationApis.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("abs"));

		try{
			Role role=new Role();
			role.setName("ROLE_ADMIN");
			role.setId(1);

			Role role1=new Role();
			role1.setName("ROLE_USER");
			role1.setId(2);

			List<Role> roles= Arrays.asList(role,role1);

			this.roleRepo.saveAll(roles);

		}catch (Exception e){
			System.out.println("Role not found");
		}
	}
}
