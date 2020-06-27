package com.application.service;

import com.application.model.inventory.Inventory;
import com.application.model.price.Price;
import com.application.model.product.Category;
import com.application.model.product.Product;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/**
 * This class is responsible for managing inventory of store.
 * <br>
 * Following services are provided by this class:
 * <li>fetchAll</li>
 * <li>delete</li>
 * <li>fetchById</li>
 * <li>add</li>
 * <li>insert</li>
 */
public class InventoryService {

	private final Inventory inventory = Inventory.getInstance();

	/**
	 * Adds provided product into the {@link Inventory}
	 *
	 * @param product {@link Product} to be added
	 */
	public void add(final Product product) {
		add(product, 1);
	}

	/**
	 * Adds provided product into the {@link Inventory}
	 *
	 * @param product {@link Product} to be added
	 * @param count   total number of pieces of product
	 */
	public void add(final Product product, final int count) {
		inventory.addToInventory(product, count);
	}

	/**
	 * Removes provided product from the {@link Inventory}
	 *
	 * @param product {@link Product} to be removed
	 */
	public void remove(final Product product) {
		remove(product, 1);
	}

	/**
	 * removes provided product from the {@link Inventory}
	 *
	 * @param product       {@link Product} to be removed
	 * @param numberofItems total number of pieces of product
	 */
	public void remove(final Product product, final int numberofItems) {
		inventory.removeFromInventory(product, numberofItems);
	}

	/**
	 * Fetches product by given ID
	 *
	 * @param productId product id to be searched
	 * @return Optional of {@link Product}
	 */
	public Optional<Product> fetchById(final long productId) {
		return inventory.getInventories().keySet().stream().filter(product -> product.getId() == productId).findFirst();
	}

	/**
	 * Fetches all products in inventory
	 *
	 * @return List of {@link Product}
	 */
	public Set<Product> fetchAll() {
		return Collections.unmodifiableSet(inventory.getInventories().keySet());
	}

	/**
	 * Checks if provided product is is available in given quantity
	 *
	 * @param productId id of product
	 * @param quantity  number of pieces of product
	 * @return true if available
	 */
	public boolean checkAvailability(final long productId, final int quantity) {
		return inventory.getInventories()
				.entrySet()
				.stream()
				.anyMatch(entry -> entry.getKey().getId() == productId && entry.getValue().intValue() >= quantity);
	}

	/**
	 * Checks if provided product is  available.
	 *
	 * @param productId id of product
	 * @return true if available
	 */
	public boolean checkAvailability(final long productId) {
		return inventory.getInventories().entrySet().stream().anyMatch(entry -> entry.getKey().getId() == productId);
	}

	/**
	 * Provides available count of items available in inventory
	 *
	 * @param productId id of product
	 * @return count of product pieces.
	 */
	public long availableCount(final long productId) {
		if (checkAvailability(productId)) {
			return inventory.getInventories()
					.entrySet()
					.stream()
					.filter(entry -> entry.getKey().getId() == productId)
					.findFirst()
					.get()
					.getValue()
					.longValue();
		}
		return -1;
	}

	public void initializeInventory() {
		add(new Product(1, "A", new Price(BigDecimal.valueOf(10).doubleValue(), "INR"), Category.CATEGORY_A), 10);
		add(new Product(2, "B", new Price(BigDecimal.valueOf(20).doubleValue(), "INR"), Category.CATEGORY_A), 10);
		add(new Product(3, "C", new Price(BigDecimal.valueOf(30).doubleValue(), "INR"), Category.CATEGORY_B), 20);
		add(new Product(4, "D", new Price(BigDecimal.valueOf(40).doubleValue(), "INR"), Category.CATEGORY_B), 20);
		add(new Product(5, "E", new Price(BigDecimal.valueOf(50).doubleValue(), "INR"), Category.CATEGORY_C), 30);
		add(new Product(6, "F", new Price(BigDecimal.valueOf(60).doubleValue(), "INR"), Category.CATEGORY_C), 30);
		add(new Product(7, "G", new Price(BigDecimal.valueOf(70).doubleValue(), "INR"), Category.CATEGORY_C), 30);
	}

}
