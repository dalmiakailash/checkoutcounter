package com.application.view;

import com.application.controller.InventoryController;

/**
 * Display underline inventory.
 */
public class InventoryView implements IView {

	private static final String VIEW_FORMAT = "|%10s|%12s|";

	private final InventoryController inventoryController;

	public InventoryView(final InventoryController inventoryController) {
		this.inventoryController = inventoryController;
	}

	@Override
	public void render() {
		System.out.println(String.format(VIEW_FORMAT, "PRODUCT_ID", "PRODUCT_NAME"));
		inventoryController.fetchInventoryProducts()
				.forEach(product -> System.out.println(String.format(VIEW_FORMAT, product.getId(), product.getName())));
	}
}
