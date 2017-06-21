package com.pintabar;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableTransactionManagement
public class PintabarServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PintabarServerApplication.class, args);
	}

	@Bean
	public SessionFactory sessionFactory(EntityManagerFactory emf) {
		if (emf.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("factory is not a hibernate factory");
		}
		return emf.unwrap(SessionFactory.class);
	}
}
