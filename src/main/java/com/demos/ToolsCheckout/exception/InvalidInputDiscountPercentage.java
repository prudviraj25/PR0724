package com.demos.ToolsCheckout.exception;

public class InvalidInputDiscountPercentage extends Exception {
	private static final long serialVersionUID = 1L;
	
	private static String defaultMessage = "Discount must be between 0-100";
    
	public InvalidInputDiscountPercentage() {
    	super(defaultMessage);
    }
}