package com.satyam.BookMyShowBackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class BookMyShowBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowBackendApplication.class, args);
	}

}
