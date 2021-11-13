package com.example.springintegrationproblems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class SpringIntegrationProblemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationProblemsApplication.class, args);
	}

}
