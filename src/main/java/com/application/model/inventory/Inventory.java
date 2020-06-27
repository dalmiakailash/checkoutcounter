package com.application.model.inventory;

import com.application.model.product.Product;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is inventory representation of store.
 * It consist of:
 * <li>List of {@link Product}</li>
 */

public final class Inventory {

	private static final Inventory INSTANCE = new Inventory();
	private final Map<Product, BigInteger> inventories = new HashMap<>();

	private Inventory() {
	}

	public static Inventory getInstance() {
		return INSTANCE;
	}

	public void addToInventory(final Product product, final int count) {
		if (inventories.containsKey(product)) {
			inventories.put(product, inventories.get(product).add(BigInteger.valueOf(count)));
		} else {
			inventories.put(product, BigInteger.valueOf(count));
		}
	}

	public void removeFromInventory(final Product product, final int count) {
		inventories.computeIfPresent(product, (k, v) -> v.subtract(BigInteger.valueOf(count)));
	}

	public Map<Product, BigInteger> getInventories() {
		return Collections.unmodifiableMap(inventories);
	}
}
