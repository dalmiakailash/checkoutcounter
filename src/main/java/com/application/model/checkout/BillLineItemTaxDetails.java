package com.application.model.checkout;

import com.application.model.price.Price;
import com.application.model.tax.ITax;

/**
 * This class holds tax details of bill line item. It holds:
 *
 * <li>ITax</li>
 * <li>Tax amount</li>
 */
public final class BillLineItemTaxDetails {

	private final ITax tax;
	private final Price taxAmount;

	public BillLineItemTaxDetails(final ITax tax, final Price taxAmount) {
		this.tax = tax;
		this.taxAmount = taxAmount;
	}

	public ITax getTax() {
		return tax;
	}

	public Price getTaxAmount() {
		return taxAmount;
	}
}
