package com.demos.ToolsCheckout;

import com.demos.ToolsCheckout.pojo.RentOrder;

/**
 * ToolsCheckout
 */
public class ToolsCheckout {
    public static void main(String[] args) {
    	try {
        	new RentOrder("JAKR", "8/31/24", 3, 0).checkout().print();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
