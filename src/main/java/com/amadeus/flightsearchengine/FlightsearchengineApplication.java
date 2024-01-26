package com.amadeus.flightsearchengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
public class FlightsearchengineApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightsearchengineApplication.class, args);
	}

}
