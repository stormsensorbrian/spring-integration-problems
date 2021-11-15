package com.example.springintegrationproblems;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
@EnableRabbit
@IntegrationComponentScan
public class SpringIntegrationProblemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationProblemsApplication.class, args);
	}

}
