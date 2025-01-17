package com.bookproject.book;

import com.bookproject.book.role.Role;
import com.bookproject.book.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@EnableJpaAuditing	// working for createdDate and LastModifiedDate, need to create class to handle createdBy and LastModifiedBy
@EnableJpaAuditing(auditorAwareRef = "auditorAware") // add the auditorAware that you created
@EnableAsync
public class BookNetworkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookNetworkApiApplication.class, args);
	}

	// USER role is not existed in database, insert the user role
	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository){
		return args -> {
			if(roleRepository.findByName("USER").isEmpty()){
				roleRepository.save(
						Role.builder().name("USER").build()
				);
			}
		};
	}
}
