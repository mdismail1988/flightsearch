package com.tokigames.assessment.flightsearch.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import com.tokigames.assessment.flightsearch.util.DateUtil;

public class EconomyClass implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String route;
	private String source;
	private String destination;
	//Timestamp
	private String departure;
	private String arrival;
	
	public EconomyClass(String route, String departure, String arrival) {
		super();
		this.route = route;
		this.departure = departure;
		this.arrival = arrival;
	}
	
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getSource() {
		splitRoute(this.route);
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		splitRoute(this.route);
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	
	public Date getDepartureTime() {
		return DateUtil.timestampToDate(((this.departure).split("\\."))[0].trim()); 
	}

	public void setDepartureTime(LocalDateTime departureTime) {
	}

	public Date getArrivalTime() {
		return DateUtil.timestampToDate(this.arrival.split("\\.")[0].trim());
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
	}

	private void splitRoute(String route) {
		String[] splitedRoute = route.split("-");
		this.source = splitedRoute[0].trim();
		this.destination = splitedRoute[1].trim();
	}
	
	

	
}
