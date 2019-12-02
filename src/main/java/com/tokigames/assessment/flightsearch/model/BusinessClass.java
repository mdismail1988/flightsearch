package com.tokigames.assessment.flightsearch.model;

import java.io.Serializable;
import java.util.Date;

import com.tokigames.assessment.flightsearch.util.DateUtil;

public class BusinessClass implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String departure;
	private String arrival;
	private String departureTime;
	private String arrivalTime;
	
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
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Date getDepartureDateTime() {
		return DateUtil.timestampToDate(((this.departureTime).split("\\."))[0].trim()); 
	}
	
	public Date getArrivalDateTime() {
		return DateUtil.timestampToDate(((this.arrivalTime).split("\\."))[0].trim()); 
	}
	
}
