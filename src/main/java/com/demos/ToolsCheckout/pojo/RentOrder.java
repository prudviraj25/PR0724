package com.demos.ToolsCheckout.pojo;

import java.time.LocalDate;

import com.demos.ToolsCheckout.exception.InvalidInputCheckoutData;
import com.demos.ToolsCheckout.exception.InvalidInputCheckoutDate;
import com.demos.ToolsCheckout.exception.InvalidInputDiscountPercentage;
import com.demos.ToolsCheckout.exception.InvalidInputRentalDays;
import com.demos.ToolsCheckout.exception.InvalidInputToolCode;
import com.demos.ToolsCheckout.util.Util;

import lombok.Data;

/**
 * Checkout
 */
@Data
public class RentOrder {

	private ToolInfo toolInfo;
	private int rentalDays;
	private int discount;
	private LocalDate checkoutDate;

	public RentOrder(String toolCode, String rentalDays, String discount, String checkoutDate)
			throws InvalidInputToolCode, InvalidInputRentalDays, InvalidInputCheckoutDate,
			InvalidInputDiscountPercentage, InvalidInputCheckoutData {
		super();
		try {
			this.setToolInfo(new ToolInfo(toolCode));

			try {
				this.setRentalDays(Integer.parseInt(rentalDays));
			} catch (Exception e) {
			}
			this.validateRentalDays();

			try {
				this.setDiscount(Integer.parseInt(discount));
			} catch (Exception e) {
			}
			this.validateDiscount();

			try {
				this.setCheckoutDate(Util.getLocalDate(checkoutDate));
			} catch (Exception e) {
				throw new InvalidInputCheckoutDate();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public RentOrder(String toolCode, String checkoutDate, int rentalDays, int discount) throws InvalidInputToolCode,
			InvalidInputRentalDays, InvalidInputCheckoutDate, InvalidInputDiscountPercentage, InvalidInputCheckoutData {
		super();
		try {
			this.setToolInfo(new ToolInfo(toolCode));

			this.setRentalDays(rentalDays);
			this.validateRentalDays();

			this.setDiscount(discount);
			this.validateDiscount();

			try {
				this.setCheckoutDate(Util.getLocalDate(checkoutDate));
			} catch (Exception e) {
				throw new InvalidInputCheckoutDate();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	private void validateRentalDays() throws InvalidInputRentalDays {
		if (this.getRentalDays() < 1) {
			throw new InvalidInputRentalDays();
		}
	}

	private void validateDiscount() throws InvalidInputDiscountPercentage {
		if (this.getDiscount() < 0 || this.getDiscount() > 100) {
			throw new InvalidInputDiscountPercentage();
		}
	}

	public RentalAgreement checkout() throws InvalidInputCheckoutData {
		if (this.getToolInfo() == null || this.getRentalDays() <= 0 || this.getCheckoutDate() == null) {
			throw new InvalidInputCheckoutData();
		}
		return new RentalAgreement(this.getToolInfo(), this.getRentalDays(), this.getDiscount(),
				this.getCheckoutDate());
	}
}
