package com.application.service;

import com.application.model.tax.ITax;
import com.application.model.tax.Percent;
import com.application.util.Calculator;

/**
 * This class calculates the tax amount on provided base amount with given tax
 */
public class TaxCalculationService {

	/**
	 * Calculates tax applied on given base value
	 *
	 * @param tax  applied tax
	 * @param base amount to be taxed
	 * @return tax amount
	 */
	public double calculateTaxAmount(final ITax tax, final double base) {
		return Calculator.divide(Calculator.multiply(tax.getPercentage().getValue(), base),
				Percent.HUNDRED_PERCENT.getValue());
	}
}
