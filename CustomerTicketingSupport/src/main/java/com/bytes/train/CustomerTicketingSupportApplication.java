package com.bytes.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.bytes")
@ComponentScan({"com.bytes.train.controller","com.bytes.train.loginauthentication","com.bytes.train.serviceImpl"})
@EnableJpaRepositories("com.bytes")
public class CustomerTicketingSupportApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerTicketingSupportApplication.class, args);
	}

}
