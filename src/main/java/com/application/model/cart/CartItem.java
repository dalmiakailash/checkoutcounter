package com.application.model.cart;

import com.application.model.product.Product;

import java.util.Objects;

/**
 * This class is representation of item added in cart. It contains:
 * <li>{@link Product} added </li>
 * <li>quantity of product</li>
 */
public class CartItem {
	private final Product product;
	private final int quantity;

	public CartItem(final Product product, final int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof CartItem)) {
			return false;
		}
		final CartItem cartItem = (CartItem) o;
		return Objects.equals(getProduct(), cartItem.getProduct());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getProduct());
	}
}
