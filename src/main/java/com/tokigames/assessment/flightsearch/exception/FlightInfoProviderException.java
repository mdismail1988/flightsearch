package com.tokigames.assessment.flightsearch.exception;

public class FlightInfoProviderException extends Exception {
	
	private static final long serialVersionUID = -123L;
	private String errorMessage;
	
	 @Override
	 public String getMessage() {
		 return errorMessage;
	 }
	 
	 public FlightInfoProviderException() {
		 super();
	 }
	 
	 public FlightInfoProviderException(String errorMessage) {
		 super(errorMessage);
		 this.errorMessage = errorMessage;
	 }

}
