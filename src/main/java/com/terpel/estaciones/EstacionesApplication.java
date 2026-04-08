package com.terpel.estaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EstacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstacionesApplication.class, args);
	}

}
