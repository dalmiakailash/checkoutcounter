package com.application.controller;

import com.application.model.checkout.Bill;
import com.application.service.CheckoutService;

/**
 * Controller for handling checkout activities
 */
public class CheckoutController {

	private final CheckoutService checkoutService;

	/**
	 * C'Tor
	 *
	 * @param checkoutService serves checkout activities
	 */
	public CheckoutController(final CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}

	/**
	 * Checkout a cart and generates bill out of it
	 *
	 * @return generated bill
	 */
	public Bill checkout() {
		return checkoutService.generateBill();
	}

}
