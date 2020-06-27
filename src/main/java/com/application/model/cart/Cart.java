package com.application.model.cart;

import com.application.model.product.Product;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Shopping cart representation, contains:
 * <li>List of shopping items</li>
 */
public final class Cart {

	private static final Cart INSTANCE = new Cart();
	private final Map<Product, BigInteger> cartItems = new HashMap<>();

	private Cart() {
	}

	public static Cart getInstance() {
		return INSTANCE;
	}

	public void addToCart(final Product product, final int count) {
		if (cartItems.containsKey(product)) {
			cartItems.put(product, cartItems.get(product).add(BigInteger.valueOf(count)));
		} else {
			cartItems.put(product, BigInteger.valueOf(count));
		}
	}

	public void removeFromCart(final Product product, final int count) {
		final BigInteger updatedCount = cartItems.computeIfPresent(product,
				(k, v) -> v.subtract(BigInteger.valueOf(count)));
		if (Objects.nonNull(updatedCount) && updatedCount.intValue() <= 0) {
			cartItems.remove(product);
		}
	}

	public void removeFromCart(final Product product) {
		cartItems.remove(product);
	}

	public Map<Product, BigInteger> getCartItems() {
		return Collections.unmodifiableMap(cartItems);
	}
}
