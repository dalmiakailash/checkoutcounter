package com.application;

import com.application.controller.CartController;
import com.application.controller.CheckoutController;
import com.application.controller.InventoryController;
import com.application.factory.ProductCategoryTaxFactory;
import com.application.model.view.View;
import com.application.service.BillLineItemCreationService;
import com.application.service.CartService;
import com.application.service.CheckoutService;
import com.application.service.InventoryService;
import com.application.service.TaxCalculationService;
import com.application.view.CartView;
import com.application.view.CheckoutView;
import com.application.view.ExitPage;
import com.application.view.IView;
import com.application.view.IndexPage;
import com.application.view.InventoryView;
import com.application.view.ShoppingView;

public final class ApplicationContext {

	private static final ApplicationContext INSTANCE = new ApplicationContext();
	private InventoryService inventoryService;
	private CartService cartService;
	private CheckoutService checkoutService;
	private InventoryController inventoryController;
	private CartController cartController;
	private CheckoutController checkoutController;
	private BillLineItemCreationService billLineItemCreationService;
	private ProductCategoryTaxFactory productCategoryTaxFactory;
	private TaxCalculationService taxCalculationService;

	private ApplicationContext() {
	}

	public static ApplicationContext getApplicationContext() {
		return INSTANCE;
	}

	public void initialize() {
		inventoryService = new InventoryService();
		inventoryService.initializeInventory();
		cartService = new CartService(inventoryService);
		productCategoryTaxFactory = new ProductCategoryTaxFactory();
		taxCalculationService = new TaxCalculationService();
		billLineItemCreationService = new BillLineItemCreationService(productCategoryTaxFactory, taxCalculationService);
		checkoutService = new CheckoutService(billLineItemCreationService, cartService);

		inventoryController = new InventoryController(inventoryService);
		cartController = new CartController(cartService);
		checkoutController = new CheckoutController(checkoutService);
	}

	/**
	 * Act as a View factory. Provides instance of required view
	 * @param view View type
	 * @return console view
	 */
	public IView getView(final View view) {
		switch (view) {
			case INDEX:
				return new IndexPage();
			case INVENTORY:
				return new InventoryView(inventoryController);
			case CART:
				return new CartView(cartController);
			case SHOPPING:
				return new ShoppingView(cartController);
			case CHECKOUT:
				return new CheckoutView(checkoutController);
			case EXIT:
				return new ExitPage();
			default:
				return null;
		}
	}
}
