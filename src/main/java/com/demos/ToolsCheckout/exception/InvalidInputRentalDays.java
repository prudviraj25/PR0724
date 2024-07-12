package com.demos.ToolsCheckout.exception;

public class InvalidInputRentalDays extends Exception {
	private static final long serialVersionUID = 1L;
	
	private static String defaultMessage = "Rental day count can not be less than 1";
    
	public InvalidInputRentalDays() {
    	super(defaultMessage);
    }
}