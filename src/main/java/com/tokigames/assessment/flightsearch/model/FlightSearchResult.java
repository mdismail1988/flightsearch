package com.tokigames.assessment.flightsearch.model;

import java.util.List;

public class FlightSearchResult {
	
	private String source;
	private String destination;
	private List<FlightTimings> onwardFlights;
	private List<FlightTimings> returnFlights;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public List<FlightTimings> getOnwardFlights() {
		return onwardFlights;
	}
	public void setOnwardFlights(List<FlightTimings> onwardFlights) {
		this.onwardFlights = onwardFlights;
	}
	public List<FlightTimings> getReturnFlights() {
		return returnFlights;
	}
	public void setReturnFlights(List<FlightTimings> returnFlights) {
		this.returnFlights = returnFlights;
	}
	

}
