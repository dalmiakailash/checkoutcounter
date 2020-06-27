package com.application.util;

import java.util.Arrays;

/**
 * Calculator utility for price calculation
 */

public final class Calculator {

	private Calculator() {
	}

	/**
	 * Adds up all provided numbers
	 *
	 * @param numbers number in double format
	 * @return sum of all numbers
	 */
	public static double add(final double... numbers) {
		return Arrays.stream(numbers).sum();
	}

	/**
	 * subtract up all provided numbers
	 *
	 * @param numbers number in double format
	 * @return subtraction of all numbers
	 */
	public static double subtract(final double... numbers) {
		return Arrays.stream(numbers).reduce((a, b) -> a - b).getAsDouble();
	}

	/**
	 * subtract up all provided numbers
	 *
	 * @param numerator   number in double format
	 * @param denominator number in double format
	 * @return division of provided numbers
	 */
	public static double divide(final double numerator, final double denominator) {
		return numerator / denominator;
	}

	/**
	 * muliply all provided numbers
	 *
	 * @param numbers list of number
	 * @return multiplication of provided numbers
	 */
	public static double multiply(final double... numbers) {
		return Arrays.stream(numbers).reduce((a, b) -> a * b).getAsDouble();
	}

}
