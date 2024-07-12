package com.demos.ToolsCheckout.exception;

public class InvalidInputCheckoutData extends Exception {
	private static final long serialVersionUID = 1L;
	
	private static String defaultMessage = "Cannot checkout with invalid data";
    
	public InvalidInputCheckoutData() {
    	super(defaultMessage);
    }
}