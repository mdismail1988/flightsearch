package com.tokigames.assessment.flightsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightSerachApp {
	public static void main( String[] args ){
	        System.out.println( "Starting FlightSerach App" );
	        SpringApplication.run(FlightSerachApp.class, args);
	}
}
