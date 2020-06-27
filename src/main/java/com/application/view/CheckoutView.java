package com.application.view;

import com.application.controller.CheckoutController;
import com.application.model.checkout.Bill;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Create checkout view
 */
public class CheckoutView implements IView {

	private static final String VIEW_FORMAT = "|%3s|%12s|%5s|%10s|%10s|%12s|%10s|";

	private final CheckoutController checkoutController;

	/**
	 * C'Tor
	 *
	 * @param checkoutController control checkout process
	 */
	public CheckoutView(final CheckoutController checkoutController) {
		this.checkoutController = Objects.requireNonNull(checkoutController, "checkoutController is NULL!!");
	}

	@Override
	public void render() {
		System.out.println("++++++++++Starting Checkout++++++++++");
		System.out.println("==========Generating Bill============");
		final Bill bill = checkoutController.checkout();
		if (Objects.nonNull(bill)) {
			System.out.println("==========Generated Bill=============");
			System.out.println(String.format(VIEW_FORMAT, "ID", "NAME", "COUNT", "COST", "TAX %", "TAX AMT", "TOTAL"));
			bill.getLineItems().forEach(billLineItem -> {
				System.out.println(String.format(VIEW_FORMAT,
						billLineItem.getBillLineItemProductDetails().getId(),
						billLineItem.getBillLineItemProductDetails().getName(),
						billLineItem.getBillLineItemProductDetails().getCount(),
						billLineItem.getBillLineItemProductDetails().getItemPrice(),
						billLineItem.getBillLineItemTaxDetails()
								.stream()
								.map(taxDetail -> taxDetail.getTax().getPercentage().toString())
								.collect(Collectors.joining("\r\n")),
						billLineItem.getBillLineItemTaxDetails()
								.stream()
								.map(taxDetail -> taxDetail.getTaxAmount().toString())
								.collect(Collectors.joining("\r\n")),
						billLineItem.getTotalLineCost()));
			});
			System.out.println(String.format(VIEW_FORMAT, "", "", "", "", "", "GRAND TOTAL", bill.getTotal()));
		} else {
			System.out.println("====Cart is empty====");
		}
	}
}
