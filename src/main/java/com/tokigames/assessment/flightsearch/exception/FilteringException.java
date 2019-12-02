package com.tokigames.assessment.flightsearch.exception;

public class FilteringException extends RuntimeException {
	
	private static final long serialVersionUID = -123L;
	private String errorMessage;
	
	 @Override
	 public String getMessage() {
		 return errorMessage;
	 }
	 
	 public FilteringException() {
		 super();
	 }
	 
	 public FilteringException(String errorMessage) {
		 super(errorMessage);
		 this.errorMessage = errorMessage;
	 }

}
