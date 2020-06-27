package com.application.service;

import com.application.model.inventory.Inventory;
import com.application.model.price.Price;
import com.application.model.product.Category;
import com.application.model.product.Product;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InventoryServiceTest {

	private final InventoryService inventoryService = new InventoryService();

	@Test
	public void shouldBeAbleToAdd(){
		//given
		final Product product_1 = new Product(1, "A", new Price(10, "INR"), Category.CATEGORY_A);

		//then
		inventoryService.add(product_1, 10);

		assertTrue(inventoryService.checkAvailability(1));
		assertEquals(10, inventoryService.availableCount(1));

		inventoryService.remove(product_1, 10);
	}

	@Test
	public void shouldBeAbleToRemoveProvidedQuantity(){
		//given
		final Product product_1 = new Product(1, "A", new Price(10, "INR"), Category.CATEGORY_A);
		Inventory.getInstance().addToInventory(product_1, 20);

		//then
		inventoryService.remove(product_1);

		assertTrue(inventoryService.checkAvailability(1));
		assertEquals(19, inventoryService.availableCount(1));

		inventoryService.remove(product_1, 19);
	}

}
