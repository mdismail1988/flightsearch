package com.tokigames.assessment.flightsearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightSerachApp {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightSerachApp.class);
	 
	public static void main( String[] args ){
			LOGGER.info("Starting FlightSerach App");
	        SpringApplication.run(FlightSerachApp.class, args);
	}
}
