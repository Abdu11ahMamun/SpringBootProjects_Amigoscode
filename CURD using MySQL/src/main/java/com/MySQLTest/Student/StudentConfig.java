package com.MySQLTest.Student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	@Bean
	CommandLineRunner commandLineRunner(
			StudentRepository repository) {
		return args->{
			student mariam=new student(
					1L,
					"Mariam",
					"mariam@gmail.com",
					LocalDate.of(2000, 1, 5)
					);
			student jamal=new student(
					2L,
					"jamal",
					"jamal@gmail.com",
					LocalDate.of(2002, 1, 9)
					);
			repository.saveAll(List.of(mariam, jamal));
			
		};
	}
}
