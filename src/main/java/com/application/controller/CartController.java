package com.application.controller;

import com.application.exception.ProductNotFoundException;
import com.application.exception.ProductShortageException;
import com.application.model.product.Product;
import com.application.service.CartService;

import java.math.BigInteger;
import java.util.Map;
import java.util.Objects;

/**
 * Controls cart activities
 */

public class CartController {

	private final CartService cartService;

	public CartController(final CartService cartService) {
		this.cartService = Objects.requireNonNull(cartService, "cartService is NULL!!");
	}

	/**
	 * Fetch complete cart elements
	 *
	 * @return map of products in cart and their counts
	 */
	public Map<Product, BigInteger> fetchCartItems() {
		return cartService.fetchCartItems();
	}

	/**
	 * Adds given product id with number of pieces in cart
	 *
	 * @param productId product to be added
	 * @param count     number of pieces to be addes
	 * @throws ProductNotFoundException In case product asked for is not available
	 * @throws ProductShortageException In case quantity asked for is not available
	 */
	public void addToCart(final int productId, final int count) throws
			ProductNotFoundException,
			ProductShortageException {
		cartService.add(productId, count);
	}

	/**
	 * Removes given product id's number of pieces from cart
	 *
	 * @param productId item id to be removed
	 * @param count     number of items to be removed
	 */
	public void removeFromCart(final int productId, final int count) {
		cartService.remove(productId, count);
	}

	/**
	 * Removes given product from cart(if available in cart)
	 *
	 * @param productId product if to be removed
	 */
	public void removeItem(final int productId) {
		cartService.removeAll(productId);
	}
}
