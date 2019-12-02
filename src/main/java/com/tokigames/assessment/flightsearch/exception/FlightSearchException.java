package com.tokigames.assessment.flightsearch.exception;

public class FlightSearchException extends RuntimeException {
	
	private static final long serialVersionUID = -123L;
	private String errorMessage;
	
	 @Override
	 public String getMessage() {
		 return errorMessage;
	 }
	 
	 public FlightSearchException() {
		 super();
	 }
	 
	 public FlightSearchException(String errorMessage) {
		 super(errorMessage);
		 this.errorMessage = errorMessage;
	 }

}
