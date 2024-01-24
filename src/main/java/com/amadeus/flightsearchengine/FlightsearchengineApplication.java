package com.amadeus.flightsearchengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class FlightsearchengineApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightsearchengineApplication.class, args);
	}

}
