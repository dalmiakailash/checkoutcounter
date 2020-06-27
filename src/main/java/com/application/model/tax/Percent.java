package com.application.model.tax;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This class is percentage representation.
 * Anywhere in project if percentage need to be described this class can be used.
 */

public final class Percent {

	public static final Percent TEN_PERCENT = new Percent(10);
	public static final Percent TWENTY_PERCENT = new Percent(20);
	public static final Percent ZERO_PERCENT = new Percent(0);
	private static final double ONE_HUNDRED_PERCENT_VALUE = 100.0;
	public static final Percent HUNDRED_PERCENT = new Percent(ONE_HUNDRED_PERCENT_VALUE);
	private final double value;

	/**
	 * Make a new percentage
	 *
	 * @param value value between 0 and 100 inclusive
	 */
	public Percent(final double value) {
		if (value < 0.0 || value > ONE_HUNDRED_PERCENT_VALUE) {
			throw new IllegalArgumentException("Percent value must be between 0 and 100 but is " + value);
		}

		this.value = value;
	}

	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return NumberFormat.getPercentInstance(Locale.getDefault()).format(value / ONE_HUNDRED_PERCENT_VALUE);
	}

}
