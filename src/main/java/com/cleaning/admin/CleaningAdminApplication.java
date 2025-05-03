package com.cleaning.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CleaningAdminApplication {

	public static void main(String[] args) {
		String port = System.getenv("PORT");
		if (port == null) {
			port = "8080"; // default for local development
		}
		SpringApplication.run(CleaningAdminApplication.class, args);
	}

}
