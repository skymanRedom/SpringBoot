package com.maven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FirstSpringApplication {

	// test git
	public static void main(String[] args) {
		SpringApplication.run(FirstSpringApplication.class, args);
	}
}
