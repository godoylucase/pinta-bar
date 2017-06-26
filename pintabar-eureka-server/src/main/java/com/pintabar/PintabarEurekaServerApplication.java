package com.pintabar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PintabarEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PintabarEurekaServerApplication.class, args);
	}
}
