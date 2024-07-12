package com.demos.ToolsCheckout;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.demos.ToolsCheckout.exception.InvalidInputDiscountPercentage;
import com.demos.ToolsCheckout.pojo.RentOrder;
import com.demos.ToolsCheckout.pojo.RentalAgreement;

class ToolsCheckoutTests {
	@BeforeEach
	@AfterEach
	private void printSeperator() {
		System.out.println("\n--------------------------------------------------");
	}

	@Test
	@DisplayName("Test 1")
	void Test1(TestInfo testInfo) {
		System.out.println("---[ " + testInfo.getDisplayName() + " ]----------");

		assertThrowsExactly(InvalidInputDiscountPercentage.class, () -> {
			RentOrder rentOrder = new RentOrder("JAKR", "9/3/15", 5, 101);
			RentalAgreement rentAgreement = rentOrder.checkout();
			rentAgreement.print();
		}, "Should throw InvalidInputDiscountPercentage exception");
	}

	@Test
	@DisplayName("Test 2")
	void Test2(TestInfo testInfo) {
		System.out.println("---[ " + testInfo.getDisplayName() + " ]----------");

		assertDoesNotThrow(() -> {
			RentOrder rentOrder = new RentOrder("LADW", "7/2/20", 3, 10);
			RentalAgreement rentAgreement = rentOrder.checkout();
			rentAgreement.print();
			assertEquals("LADW", rentAgreement.getToolCode(), "Tool code should match");
			assertEquals("Ladder", rentAgreement.getToolType(), "Tool type should match");
			assertEquals("Werner", rentAgreement.getToolBrand(), "Tool brand should match");
			assertEquals(3, rentAgreement.getRentalDays(), "Rental days should match");
			assertEquals("7/2/20", rentAgreement.getCheckoutDate(), "Check out date should match");
			assertEquals("7/4/20", rentAgreement.getDueDate(), "Due date should match");
			assertEquals("$1.99", rentAgreement.getDailyRentalCharge(), "Daily rental charge should match");
			assertEquals(2, rentAgreement.getChargeDays(), "Charge days should match");
			assertEquals("$3.98", rentAgreement.getPreDiscountCharge(), "Pre-discount charge should match");
			assertEquals("10%", rentAgreement.getDiscountPercent(), "Discount percent should match");
			assertEquals("$0.40", rentAgreement.getDiscountAmount(), "Discount amount should match");
			assertEquals("$3.58", rentAgreement.getFinalCharge(), "Final charge should match");
		}, "Should not throw any exception");
	}

	@Test
	@DisplayName("Test 3")
	void Test3(TestInfo testInfo) {
		System.out.println("---[ " + testInfo.getDisplayName() + " ]----------");

		assertDoesNotThrow(() -> {
			RentOrder rentOrder = new RentOrder("CHNS", "7/2/15", 5, 25);
			RentalAgreement rentAgreement = rentOrder.checkout();
			rentAgreement.print();
			assertEquals("CHNS", rentAgreement.getToolCode(), "Tool code should match");
			assertEquals("Chainsaw", rentAgreement.getToolType(), "Tool type should match");
			assertEquals("Stihl", rentAgreement.getToolBrand(), "Tool brand should match");
			assertEquals(5, rentAgreement.getRentalDays(), "Rental days should match");
			assertEquals("7/2/15", rentAgreement.getCheckoutDate(), "Check out date should match");
			assertEquals("7/6/15", rentAgreement.getDueDate(), "Due date should match");
			assertEquals("$1.49", rentAgreement.getDailyRentalCharge(), "Daily rental charge should match");
			assertEquals(4, rentAgreement.getChargeDays(), "Charge days should match");
			assertEquals("$5.96", rentAgreement.getPreDiscountCharge(), "Pre-discount charge should match");
			assertEquals("25%", rentAgreement.getDiscountPercent(), "Discount percent should match");
			assertEquals("$1.49", rentAgreement.getDiscountAmount(), "Discount amount should match");
			assertEquals("$4.47", rentAgreement.getFinalCharge(), "Final charge should match");
		}, "Should not throw any exception");
	}

	@Test
	@DisplayName("Test 4")
	void Test4(TestInfo testInfo) {
		System.out.println("---[ " + testInfo.getDisplayName() + " ]----------");

		assertDoesNotThrow(() -> {
			RentOrder rentOrder = new RentOrder("JAKD", "9/3/15", 6, 0);
			RentalAgreement rentAgreement = rentOrder.checkout();
			rentAgreement.print();
			assertEquals("JAKD", rentAgreement.getToolCode(), "Tool code should match");
			assertEquals("Jackhammer", rentAgreement.getToolType(), "Tool type should match");
			assertEquals("DeWalt", rentAgreement.getToolBrand(), "Tool brand should match");
			assertEquals(6, rentAgreement.getRentalDays(), "Rental days should match");
			assertEquals("9/3/15", rentAgreement.getCheckoutDate(), "Check out date should match");
			assertEquals("9/8/15", rentAgreement.getDueDate(), "Due date should match");
			assertEquals("$2.99", rentAgreement.getDailyRentalCharge(), "Daily rental charge should match");
			assertEquals(4, rentAgreement.getChargeDays(), "Charge days should match");
			assertEquals("$11.96", rentAgreement.getPreDiscountCharge(), "Pre-discount charge should match");
			assertEquals("0%", rentAgreement.getDiscountPercent(), "Discount percent should match");
			assertEquals("$0.00", rentAgreement.getDiscountAmount(), "Discount amount should match");
			assertEquals("$11.96", rentAgreement.getFinalCharge(), "Final charge should match");
		}, "Should not throw any exception");
	}

	@Test
	@DisplayName("Test 5")
	void Test5(TestInfo testInfo) {
		System.out.println("---[ " + testInfo.getDisplayName() + " ]----------");

		assertDoesNotThrow(() -> {
			RentOrder rentOrder = new RentOrder("JAKR", "7/2/15", 9, 0);
			RentalAgreement rentAgreement = rentOrder.checkout();
			rentAgreement.print();
			assertEquals("JAKR", rentAgreement.getToolCode(), "Tool code should match");
			assertEquals("Jackhammer", rentAgreement.getToolType(), "Tool type should match");
			assertEquals("Ridgid", rentAgreement.getToolBrand(), "Tool brand should match");
			assertEquals(9, rentAgreement.getRentalDays(), "Rental days should match");
			assertEquals("7/2/15", rentAgreement.getCheckoutDate(), "Check out date should match");
			assertEquals("7/10/15", rentAgreement.getDueDate(), "Due date should match");
			assertEquals("$2.99", rentAgreement.getDailyRentalCharge(), "Daily rental charge should match");
			assertEquals(6, rentAgreement.getChargeDays(), "Charge days should match");
			assertEquals("$17.94", rentAgreement.getPreDiscountCharge(), "Pre-discount charge should match");
			assertEquals("0%", rentAgreement.getDiscountPercent(), "Discount percent should match");
			assertEquals("$0.00", rentAgreement.getDiscountAmount(), "Discount amount should match");
			assertEquals("$17.94", rentAgreement.getFinalCharge(), "Final charge should match");
		}, "Should not throw any exception");
	}

	@Test
	@DisplayName("Test 6")
	void Test6(TestInfo testInfo) {
		System.out.println("---[ " + testInfo.getDisplayName() + " ]----------");

		assertDoesNotThrow(() -> {
			RentOrder rentOrder = new RentOrder("JAKR", "7/2/20", 4, 50);
			RentalAgreement rentAgreement = rentOrder.checkout();
			rentAgreement.print();
			assertEquals("JAKR", rentAgreement.getToolCode(), "Tool code should match");
			assertEquals("Jackhammer", rentAgreement.getToolType(), "Tool type should match");
			assertEquals("Ridgid", rentAgreement.getToolBrand(), "Tool brand should match");
			assertEquals(4, rentAgreement.getRentalDays(), "Rental days should match");
			assertEquals("7/2/20", rentAgreement.getCheckoutDate(), "Check out date should match");
			assertEquals("7/5/20", rentAgreement.getDueDate(), "Due date should match");
			assertEquals("$2.99", rentAgreement.getDailyRentalCharge(), "Daily rental charge should match");
			assertEquals(1, rentAgreement.getChargeDays(), "Charge days should match");
			assertEquals("$2.99", rentAgreement.getPreDiscountCharge(), "Pre-discount charge should match");
			assertEquals("50%", rentAgreement.getDiscountPercent(), "Discount percent should match");
			assertEquals("$1.50", rentAgreement.getDiscountAmount(), "Discount amount should match");
			assertEquals("$1.50", rentAgreement.getFinalCharge(), "Final charge should match");
		}, "Should not throw any exception");
	}

}