package com.application.service;

import com.application.factory.ProductCategoryTaxFactory;
import com.application.model.checkout.BillLineItem;
import com.application.model.price.Price;
import com.application.model.product.Category;
import com.application.model.product.Product;
import com.application.model.tax.ITax;
import com.application.model.tax.Percent;
import com.application.model.tax.SalesTax;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class BillLineItemCreationServiceTest {

	@Mock
	private ProductCategoryTaxFactory productCategoryTaxFactory;

	@Mock
	private TaxCalculationService taxCalculationService;

	@InjectMocks
	private BillLineItemCreationService billLineItemCreationService;

	@Test
	public void shouldBeAbleToGenerateBillLineItem_WithSingleTax() {
		//given
		final Product product = new Product(1, "Test A", new Price(10, "INR"), Category.CATEGORY_A);
		final int count = 1;
		final ITax tax = new SalesTax(Percent.TEN_PERCENT);

		//when
		when(productCategoryTaxFactory.getTaxes(Category.CATEGORY_A)).thenReturn(Arrays.asList(tax));
		when(taxCalculationService.calculateTaxAmount(tax, 10)).thenReturn(1.0);

		//then
		final BillLineItem billLineItem = billLineItemCreationService.createBillLineItem(product, count);
		assertNotNull(billLineItem);
		assertNotNull(billLineItem.getBillLineItemProductDetails());
		assertNotNull(billLineItem.getBillLineItemTaxDetails());
		assertNotNull(billLineItem.getTotalLineCost());

		assertEquals(1, billLineItem.getBillLineItemTaxDetails().size());
		assertEquals(11.0, billLineItem.getTotalLineCost().getAmount(), 0);
		assertEquals(1.0, billLineItem.getBillLineItemTaxDetails().get(0).getTaxAmount().getAmount(), 0);
		assertEquals(tax, billLineItem.getBillLineItemTaxDetails().get(0).getTax());
	}

	@Test
	public void shouldBeAbleToGenerateBillLineItem_WithMultipleTaxes() {
		//given
		final Product product = new Product(1, "Test A", new Price(10, "INR"), Category.CATEGORY_A);
		final int count = 1;
		final ITax tax_1 = new SalesTax(Percent.TEN_PERCENT);
		final ITax tax_2 = new SalesTax(Percent.TWENTY_PERCENT);

		//when
		when(productCategoryTaxFactory.getTaxes(Category.CATEGORY_A)).thenReturn(Arrays.asList(tax_1, tax_2));
		when(taxCalculationService.calculateTaxAmount(tax_1, 10)).thenReturn(1.0);
		when(taxCalculationService.calculateTaxAmount(tax_2, 10)).thenReturn(2.0);

		//then
		final BillLineItem billLineItem = billLineItemCreationService.createBillLineItem(product, count);
		assertNotNull(billLineItem);
		assertNotNull(billLineItem.getBillLineItemProductDetails());
		assertNotNull(billLineItem.getBillLineItemTaxDetails());
		assertNotNull(billLineItem.getTotalLineCost());

		assertEquals(2, billLineItem.getBillLineItemTaxDetails().size());
		assertEquals(13.0, billLineItem.getTotalLineCost().getAmount(), 0);
		assertEquals(1.0, billLineItem.getBillLineItemTaxDetails().get(0).getTaxAmount().getAmount(), 0);
		assertEquals(2.0, billLineItem.getBillLineItemTaxDetails().get(1).getTaxAmount().getAmount(), 0);
		assertEquals(tax_1, billLineItem.getBillLineItemTaxDetails().get(0).getTax());
		assertEquals(tax_2, billLineItem.getBillLineItemTaxDetails().get(1).getTax());
	}

}
