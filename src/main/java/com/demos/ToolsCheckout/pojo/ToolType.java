package com.demos.ToolsCheckout.pojo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.demos.ToolsCheckout.exception.InvalidInputCheckoutDate;
import com.demos.ToolsCheckout.util.Util;

import lombok.Data;

/**
 * ToolType
 */
@Data
public class ToolType {
	static public enum Type {
		Ladder, Chainsaw, Jackhammer;

		@Override
		public String toString() {
			return this.name();
		}
	}

	static private Map<Type, Double> dailyChargeMap = new HashMap<>();
	static private Map<Type, Boolean> weekdayChargeMap = new HashMap<>();
	static private Map<Type, Boolean> weekendChargeMap = new HashMap<>();
	static private Map<Type, Boolean> holidayChargeMap = new HashMap<>();

	static {
		dailyChargeMap.put(Type.Ladder, 1.99);
		weekdayChargeMap.put(Type.Ladder, true);
		weekendChargeMap.put(Type.Ladder, true);
		holidayChargeMap.put(Type.Ladder, false);

		dailyChargeMap.put(Type.Chainsaw, 1.49);
		weekdayChargeMap.put(Type.Chainsaw, true);
		weekendChargeMap.put(Type.Chainsaw, true);
		holidayChargeMap.put(Type.Chainsaw, false);

		dailyChargeMap.put(Type.Jackhammer, 2.99);
		weekdayChargeMap.put(Type.Jackhammer, true);
		weekendChargeMap.put(Type.Jackhammer, false);
		holidayChargeMap.put(Type.Jackhammer, false);
	}

	private Type type;

	public ToolType(Type type) {
		super();
		this.setType(type);
	}

	/**
	 * Get charge for the given date considering for weekend, holiday & weekday
	 * charging conditions
	 * 
	 * @param date
	 * @return charge for this tool type for the given date
	 * @throws InvalidInputCheckoutDate when date is not valid/null
	 */
	public double getChargeForTheDay(String date) throws InvalidInputCheckoutDate {
		try {
			return this.getChargeForTheDay(Util.getLocalDate(date));
		} catch (Exception e) {
			throw new InvalidInputCheckoutDate();
		}
	}

	/**
	 * Get charge for the given date considering for weekend, holiday & weekday
	 * charging conditions
	 * 
	 * @param localDate
	 * @return charge for this tool type for the given date
	 * @throws InvalidInputCheckoutDate when date is not valid/null
	 */
	public double getChargeForTheDay(LocalDate localDate) throws InvalidInputCheckoutDate {
		try {
			boolean isWeekend = Util.isWeekend(localDate);
			if (isWeekend && !weekendChargeMap.get(this.getType())) {
				return 0;
			}

			boolean isHoliday = Util.isHoliday(localDate);
			if (isHoliday && !holidayChargeMap.get(this.getType())) {
				return 0;
			}

			boolean isWeekDay = !isWeekend;
			if (isWeekDay && !weekdayChargeMap.get(this.getType())) {
				return 0;
			}

			return dailyChargeMap.get(this.getType());
		} catch (Exception e) {
			throw new InvalidInputCheckoutDate();
		}
	}
	
	/**
	 * Get default daily charge
	 * @return daily charge
	 */
	public double getDefaultDailyCharge() {
		return dailyChargeMap.get(this.getType());
	}

	@Override
	public String toString() {
		return this.getType().toString();
	}

}
