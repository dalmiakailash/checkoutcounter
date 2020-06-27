package com.application.service;

import com.application.model.checkout.Bill;
import com.application.model.checkout.BillLineItem;
import com.application.model.price.Price;
import com.application.model.product.Product;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Class is responsible for checkout process of cart
 */
public class CheckoutService {

	private final BillLineItemCreationService billLineItemCreationService;
	private final CartService cartService;

	public CheckoutService(
			final BillLineItemCreationService billLineItemCreationService, final CartService cartService) {
		this.billLineItemCreationService = Objects.requireNonNull(billLineItemCreationService,
				"billLineItemCreationService is NULL!!");
		this.cartService = Objects.requireNonNull(cartService, "cartService is NULL!!");
	}

	/**
	 * Generates bill for provided cart
	 *
	 * @return System bill for given cart
	 */
	public Bill generateBill() {
		if (cartService.isNotEmpty()) {
			final List<BillLineItem> lineItems = new ArrayList<>();
			final Map<Product, BigInteger> cartItems = cartService.fetchCartItems();
			cartItems.entrySet()
					.forEach(entry -> lineItems.add(billLineItemCreationService.createBillLineItem(entry.getKey(),
							entry.getValue().intValue())));
			final String currency = lineItems.get(0).getTotalLineCost().getCurrency();
			final double totalBillAmount = accumulateLineItemCost(lineItems);
			final Bill bill = new Bill(lineItems, new Price(totalBillAmount, currency));
			cartService.resetCart();
			return bill;
		}
		return null;
	}

	private double accumulateLineItemCost(final List<BillLineItem> lineItems) {
		return lineItems.stream().mapToDouble(lineItem -> lineItem.getTotalLineCost().getAmount()).sum();
	}
}
