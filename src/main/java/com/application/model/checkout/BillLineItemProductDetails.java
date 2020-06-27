package com.application.model.checkout;

import com.application.model.price.Price;

/**
 * Line Item Product details are represented by this class
 */
public final class BillLineItemProductDetails {
	private final long id;
	private final String name;
	private final Price itemPrice;
	private final int count;

	public BillLineItemProductDetails(final long id, final String name, final Price itemPrice, final int count) {
		this.id = id;
		this.name = name;
		this.itemPrice = itemPrice;
		this.count = count;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Price getItemPrice() {
		return itemPrice;
	}

	public int getCount() {
		return count;
	}
}
