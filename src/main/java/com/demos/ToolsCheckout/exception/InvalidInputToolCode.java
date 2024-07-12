package com.demos.ToolsCheckout.exception;

public class InvalidInputToolCode extends Exception {
	private static final long serialVersionUID = 1L;
	
	private static String defaultMessage = "Invalid tool code provided";
    
	public InvalidInputToolCode() {
    	super(defaultMessage);
    }
}