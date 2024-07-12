package com.demos.ToolsCheckout.util;

import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Utility for Calculating Holiday
 */
public class Util {
	public static Locale locale = new Locale.Builder().setLanguage("en").setRegion("US").build();
	public static DateTimeFormatter defaultDateFormatter = DateTimeFormatter.ofPattern("M/d/yy", locale);
	public static NumberFormat defaultCurrencyFormatter = NumberFormat.getCurrencyInstance(locale);

	/**
	 * Get real calendar date
	 * 
	 * @param {String} date
	 * @return LocalDate if date is valid
     * @throws DateTimeParseException when date is not valid/null
	 */
	public static LocalDate getLocalDate(String date) throws DateTimeParseException {
		LocalDate localDate = LocalDate.parse(date, defaultDateFormatter);
		return localDate;
	}

	/**
	 * Check if LocalDate is a Weekend or not
	 * 
	 * @param {String} localDate
	 * @return `true` if given LocalDate is a weekend (Saturday/Sunday) else `false`
     * @throws Exception when date is not valid/null
	 */
	public static boolean isWeekend(LocalDate localDate) throws Exception {
		if (localDate == null) {
			throw new Exception("Invalid localDate provided");
		}
		return localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY;
	}

	/**
	 * Check if LocalDate is a Holiday or not
	 * 
	 * @param {String} localDate
	 * @return `true` if given LocalDate is a holiday else `false`
     * @throws Exception when date is not valid/null
	 */
	public static boolean isHoliday(LocalDate localDate) throws Exception {
		if (localDate == null) {
			throw new Exception("Invalid localDate provided");
		}
		return isIndependenceDay(localDate) || isLaborDay(localDate);
	}

	/**
	 * Get default formatted date from LocalDate
	 * 
	 * @param {LocalDate} date
	 * @return Date string in default format if date is valid
     * @throws Exception when date is not valid/null
	 */
	public static String toDefaultDateFormat(LocalDate localDate) throws Exception {
		try {
			return defaultDateFormatter.format(localDate);
		} catch (DateTimeParseException e) {
			throw new Exception("Invalid localDate provided");
		}
	}

	/**
	 * Get default formatted currency from double
	 * 
	 * @param {Double} amount
	 * @return Currency string in default format if amount is valid
     * @throws Exception when amount is not valid/null
	 */
	public static String toDefaultCurrencyFormat(Double amount) throws Exception  {
		try {
			return defaultCurrencyFormatter.format(amount);
		} catch (DateTimeParseException e) {
			throw new Exception("Invalid amount provided");
		}
	}

	/**
	 * Check if LocalDate is Independence Day or not
	 * @description July 4th - If falls on weekend, it is observed on the closest weekday (if Sat, then Friday before, if Sunday, then Monday after
	 * 
	 * @param localDate
	 * @return `true` if given LocalDate is Independence Day else `false`
     * @throws Exception when date is not valid/null
	 */
	private static boolean isIndependenceDay(LocalDate localDate) {
		if (localDate.getMonth() == Month.JULY
			&& (
				(localDate.getDayOfMonth() == 4 && localDate.getDayOfWeek() != DayOfWeek.SATURDAY && localDate.getDayOfWeek() != DayOfWeek.SUNDAY)
				|| (localDate.getDayOfMonth() == 3 && localDate.getDayOfWeek() == DayOfWeek.FRIDAY)
				|| (localDate.getDayOfMonth() == 5 && localDate.getDayOfWeek() == DayOfWeek.MONDAY)
			)
		) {
			return true;
		}
		return false;
	}

	/**
	 * Check if LocalDate is Labor Day or not
	 * @description First Monday in September
	 * 
	 * @param localDate
	 * @return `true` if given LocalDate is Labor Day else `false`
     * @throws Exception when date is not valid/null
	 */
	private static boolean isLaborDay(LocalDate localDate) {
		if (localDate.getMonth() == Month.SEPTEMBER && localDate.getDayOfWeek() == DayOfWeek.MONDAY && localDate.getDayOfMonth() < 7) {
			return true;
		}
		return false;
	}
}
