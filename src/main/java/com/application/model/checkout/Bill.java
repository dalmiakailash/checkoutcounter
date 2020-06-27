package com.application.model.checkout;

import com.application.model.price.Price;

import java.util.Collections;
import java.util.List;

/**
 * Bill representation.
 * Contains:
 * <li>bill line items</li>
 * <li>Grand total</li>
 */
public final class Bill {

	private final List<BillLineItem> lineItems;
	private final Price total;

	public Bill(final List<BillLineItem> lineItems, final Price total) {
		this.lineItems = lineItems;
		this.total = total;
	}

	public List<BillLineItem> getLineItems() {
		return Collections.unmodifiableList(lineItems);
	}

	public Price getTotal() {
		return total;
	}
}
