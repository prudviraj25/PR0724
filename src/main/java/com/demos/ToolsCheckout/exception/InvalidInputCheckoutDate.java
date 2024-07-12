package com.demos.ToolsCheckout.exception;

public class InvalidInputCheckoutDate extends Exception {
	private static final long serialVersionUID = 1L;
	
	private static String defaultMessage = "Invalid checkout date provided";
    
	public InvalidInputCheckoutDate() {
    	super(defaultMessage);
    }
}