package com.application.model.tax;

/**
 * This is tax interface. It contains:
 * - percentage amount of tax
 */
public interface ITax {

	/**
	 * Fetch the % of tax
	 *
	 * @return tax
	 */
	Percent getPercentage();

}
