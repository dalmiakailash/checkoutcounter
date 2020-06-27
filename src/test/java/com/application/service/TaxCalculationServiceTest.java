package com.application.service;

import com.application.model.tax.Percent;
import com.application.model.tax.SalesTax;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaxCalculationServiceTest {

	private final TaxCalculationService taxCalculationService = new TaxCalculationService();

	@Test
	public void shouldBeAbleToCalculateCorrectTax() {
		assertEquals(1.0, taxCalculationService.calculateTaxAmount(new SalesTax(Percent.TEN_PERCENT), 10.0), 0);
		assertEquals(2.0, taxCalculationService.calculateTaxAmount(new SalesTax(Percent.TWENTY_PERCENT), 10.0), 0);
		assertEquals(3.0, taxCalculationService.calculateTaxAmount(new SalesTax(new Percent(30)), 10.0), 0);
		assertEquals(4.0, taxCalculationService.calculateTaxAmount(new SalesTax(new Percent(40)), 10.0), 0);
		assertEquals(5.0, taxCalculationService.calculateTaxAmount(new SalesTax(new Percent(50)), 10.0), 0);
		assertEquals(6.0, taxCalculationService.calculateTaxAmount(new SalesTax(new Percent(60)), 10.0), 0);
		assertEquals(7.5, taxCalculationService.calculateTaxAmount(new SalesTax(Percent.TEN_PERCENT), 75.0), 0);
		assertEquals(9.5, taxCalculationService.calculateTaxAmount(new SalesTax(Percent.TEN_PERCENT), 95.0), 0);
	}

}
