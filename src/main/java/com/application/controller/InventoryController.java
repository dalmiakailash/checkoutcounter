package com.application.controller;

import com.application.model.product.Product;
import com.application.service.InventoryService;

import java.util.Objects;
import java.util.Set;

/**
 * Controls Inventory servicing
 */
public class InventoryController {

	private final InventoryService service;

	public InventoryController(final InventoryService service) {
		this.service = Objects.requireNonNull(service, "service is NULL!!");
	}

	/**
	 * Fetch all products available in Inventory
	 *
	 * @return
	 */
	public Set<Product> fetchInventoryProducts() {
		return service.fetchAll();
	}

}
