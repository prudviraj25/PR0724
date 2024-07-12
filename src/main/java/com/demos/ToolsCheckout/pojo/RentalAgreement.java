package com.demos.ToolsCheckout.pojo;

import java.time.LocalDate;

import com.demos.ToolsCheckout.exception.InvalidInputCheckoutData;
import com.demos.ToolsCheckout.util.Util;

import lombok.Data;

/**
 * Rental Agreement
 */
@Data
public class RentalAgreement {
	private String toolCode;
	private String toolType;
	private String toolBrand;
	private int rentalDays;
	private String checkoutDate;
	private String dueDate;
	private String dailyRentalCharge;
	private int chargeDays;
	private String preDiscountCharge;
	private String discountPercent;
	private String discountAmount;
	private String finalCharge;

	/**
	 * Rent Agreement
	 * 
	 * @param toolInfo
	 * @param rentalDays
	 * @param discountPercentage
	 * @param checkoutDate
	 * @throws InvalidInputCheckoutData
	 */
	public RentalAgreement(ToolInfo toolInfo, int rentalDays, int discountPercentage, LocalDate checkoutDate)
			throws InvalidInputCheckoutData {
		try {
			this.setToolCode(toolInfo.getCode().toString());
			this.setToolType(toolInfo.getType().toString());
			this.setToolBrand(toolInfo.getBrand().toString());
			this.setRentalDays(rentalDays);
			this.setDiscountPercent(discountPercentage + "%");
			this.setCheckoutDate(Util.toDefaultDateFormat(checkoutDate));
			this.setDueDate(Util.toDefaultDateFormat(checkoutDate.plusDays(rentalDays - 1)));
			this.setDailyRentalCharge(Util.toDefaultCurrencyFormat(toolInfo.getType().getDefaultDailyCharge()));
			this.setChargeDays(0);
			this.setPreDiscountCharge("$0.00");
			this.setDiscountAmount("$0.00");
			this.setFinalCharge("$0.00");

			double preDiscountCharge = 0;
			double discountAmount = 0;
			for (int i = 0; i < this.getRentalDays(); i++) {
				LocalDate lDate = checkoutDate.plusDays(i);
				double chargeForTheDay = toolInfo.getType().getChargeForTheDay(lDate);
				if (chargeForTheDay == 0.0) {
					continue;
				}
				this.chargeDays++;
				preDiscountCharge += chargeForTheDay;
			}
			this.setPreDiscountCharge(Util.toDefaultCurrencyFormat(preDiscountCharge));
			
			if (discountPercentage > 0 && preDiscountCharge > 0) {
				discountAmount = preDiscountCharge * discountPercentage / 100D;
				this.setDiscountAmount(Util.toDefaultCurrencyFormat(discountAmount));
			}
			double finalAmount = preDiscountCharge - discountAmount;
			this.setFinalCharge(Util.toDefaultCurrencyFormat(finalAmount));
		} catch (Exception e) {
			throw new InvalidInputCheckoutData();
		}
	}

	public void print() throws InvalidInputCheckoutData {
		if (this.getToolCode() == null || this.getRentalDays() <= 0 || this.getCheckoutDate() == null) {
			throw new InvalidInputCheckoutData();
		}
		System.out.println(this);
	}

	@Override
	public String toString() {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Tool code: " 			+ this.getToolCode() 			+ '\n');
			sb.append("Tool type: " 			+ this.getToolType() 			+ '\n');
			sb.append("Tool brand: " 			+ this.getToolBrand() 			+ '\n');
			sb.append("Rental days: " 			+ this.getRentalDays() 			+ '\n');
			sb.append("Check out date: " 		+ this.getCheckoutDate() 		+ '\n');
			sb.append("Due date: " 				+ this.getDueDate() 			+ '\n');
			sb.append("Daily rental charge: " 	+ this.getDailyRentalCharge()	+ '\n');
			sb.append("Charge days: " 			+ this.getChargeDays() 			+ '\n');
			sb.append("Pre-discount charge: " 	+ this.getPreDiscountCharge()	+ '\n');
			sb.append("Discount percent: " 		+ this.getDiscountPercent()		+ '\n');
			sb.append("Discount amount: " 		+ this.getDiscountAmount()		+ '\n');
			sb.append("Final charge: " 			+ this.getFinalCharge());
			return sb.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

}
