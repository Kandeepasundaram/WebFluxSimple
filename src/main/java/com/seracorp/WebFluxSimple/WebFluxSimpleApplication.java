package com.seracorp.WebFluxSimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class WebFluxSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxSimpleApplication.class, args);
	}
}
