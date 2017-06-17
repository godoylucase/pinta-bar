package com.pintabar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PintabarServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PintabarServerApplication.class, args);
	}
}
