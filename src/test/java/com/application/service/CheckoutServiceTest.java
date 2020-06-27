package com.application.service;

import com.application.model.checkout.Bill;
import com.application.model.checkout.BillLineItem;
import com.application.model.checkout.BillLineItemProductDetails;
import com.application.model.checkout.BillLineItemTaxDetails;
import com.application.model.price.Price;
import com.application.model.product.Category;
import com.application.model.product.Product;
import com.application.model.tax.Percent;
import com.application.model.tax.SalesTax;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class CheckoutServiceTest {

	@Mock
	private BillLineItemCreationService billLineItemCreationService;

	@Mock
	private CartService cartService;

	@InjectMocks
	private CheckoutService checkoutService;

	@Test
	public void shouldBeAbleToGenerateBill_WithOneProductEachCategory() {
		//given
		final Product product_A = new Product(1, "A", new Price(10, "INR"), Category.CATEGORY_A);
		final Product product_B = new Product(2, "B", new Price(20, "INR"), Category.CATEGORY_B);
		final Product product_C = new Product(3, "C", new Price(30, "INR"), Category.CATEGORY_C);
		final Map<Product, BigInteger> cartItems = new HashMap<>();
		cartItems.put(product_A, BigInteger.ONE);
		cartItems.put(product_B, BigInteger.ONE);
		cartItems.put(product_C, BigInteger.ONE);

		final BillLineItem billLineItem_A = new BillLineItem(new BillLineItemProductDetails(1,
				"A",
				new Price(10, "INR"),
				1),
				Arrays.asList(new BillLineItemTaxDetails(new SalesTax(Percent.TEN_PERCENT), new Price(1, "INR"))),
				new Price(11, "INR"));
		final BillLineItem billLineItem_B = new BillLineItem(new BillLineItemProductDetails(1,
				"B",
				new Price(20, "INR"),
				1),
				Arrays.asList(new BillLineItemTaxDetails(new SalesTax(Percent.TWENTY_PERCENT), new Price(4, "INR"))),
				new Price(24, "INR"));
		final BillLineItem billLineItem_C = new BillLineItem(new BillLineItemProductDetails(1,
				"A",
				new Price(30, "INR"),
				1),
				Arrays.asList(new BillLineItemTaxDetails(new SalesTax(Percent.ZERO_PERCENT), new Price(0, "INR"))),
				new Price(30, "INR"));

		//when
		when(cartService.isNotEmpty()).thenReturn(true);
		when(cartService.fetchCartItems()).thenReturn(cartItems);
		when(billLineItemCreationService.createBillLineItem(product_A, 1)).thenReturn(billLineItem_A);
		when(billLineItemCreationService.createBillLineItem(product_B, 1)).thenReturn(billLineItem_B);
		when(billLineItemCreationService.createBillLineItem(product_C, 1)).thenReturn(billLineItem_C);

		//then
		final Bill bill = checkoutService.generateBill();
		assertNotNull(bill);
		assertNotNull(bill.getLineItems());
		assertNotNull(bill.getTotal());

		assertEquals(3, bill.getLineItems().size());
		assertEquals(65, bill.getTotal().getAmount(), 0);
	}

	@Test
	public void shouldBeAbleToGenerateBill_WithMultipleProductEachCategory() {
		//given
		final Product product_A = new Product(1, "A", new Price(10, "INR"), Category.CATEGORY_A);
		final Product product_B = new Product(2, "B", new Price(20, "INR"), Category.CATEGORY_B);
		final Product product_C = new Product(3, "C", new Price(30, "INR"), Category.CATEGORY_C);
		final Map<Product, BigInteger> cartItems = new HashMap<>();
		cartItems.put(product_A, BigInteger.TEN);
		cartItems.put(product_B, BigInteger.TEN);
		cartItems.put(product_C, BigInteger.TEN);

		final BillLineItem billLineItem_A = new BillLineItem(new BillLineItemProductDetails(1,
				"A",
				new Price(10, "INR"),
				10),
				Arrays.asList(new BillLineItemTaxDetails(new SalesTax(Percent.TEN_PERCENT), new Price(10, "INR"))),
				new Price(110, "INR"));
		final BillLineItem billLineItem_B = new BillLineItem(new BillLineItemProductDetails(1,
				"B",
				new Price(20, "INR"),
				10),
				Arrays.asList(new BillLineItemTaxDetails(new SalesTax(Percent.TWENTY_PERCENT), new Price(40, "INR"))),
				new Price(240, "INR"));
		final BillLineItem billLineItem_C = new BillLineItem(new BillLineItemProductDetails(1,
				"A",
				new Price(30, "INR"),
				10),
				Arrays.asList(new BillLineItemTaxDetails(new SalesTax(Percent.ZERO_PERCENT), new Price(0, "INR"))),
				new Price(300, "INR"));

		//when
		when(cartService.isNotEmpty()).thenReturn(true);
		when(cartService.fetchCartItems()).thenReturn(cartItems);
		when(billLineItemCreationService.createBillLineItem(product_A, 10)).thenReturn(billLineItem_A);
		when(billLineItemCreationService.createBillLineItem(product_B, 10)).thenReturn(billLineItem_B);
		when(billLineItemCreationService.createBillLineItem(product_C, 10)).thenReturn(billLineItem_C);

		//then
		final Bill bill = checkoutService.generateBill();
		assertNotNull(bill);
		assertNotNull(bill.getLineItems());
		assertNotNull(bill.getTotal());

		assertEquals(3, bill.getLineItems().size());
		assertEquals(650, bill.getTotal().getAmount(), 0);
	}

}
