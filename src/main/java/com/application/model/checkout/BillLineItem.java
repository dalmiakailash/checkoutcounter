package com.application.model.checkout;

import com.application.model.price.Price;

import java.util.List;

/**
 * Represents single line on a bill.
 * <li>Item Id</li>
 * <li>Item Name</li>
 * <li>Item Count</li>
 * <li>Applicable Taxes</li>
 * <li>Total</li>
 */
public final class BillLineItem {

	private final BillLineItemProductDetails billLineItemProductDetails;
	private final List<BillLineItemTaxDetails> billLineItemTaxDetails;
	private final Price totalLineCost;

	public BillLineItem(
			final BillLineItemProductDetails billLineItemProductDetails,
			final List<BillLineItemTaxDetails> billLineItemTaxDetails,
			final Price totalLineCost) {
		this.billLineItemProductDetails = billLineItemProductDetails;
		this.billLineItemTaxDetails = billLineItemTaxDetails;
		this.totalLineCost = totalLineCost;
	}

	public BillLineItemProductDetails getBillLineItemProductDetails() {
		return billLineItemProductDetails;
	}

	public List<BillLineItemTaxDetails> getBillLineItemTaxDetails() {
		return billLineItemTaxDetails;
	}

	public Price getTotalLineCost() {
		return totalLineCost;
	}
}
