package com.application.view;

import com.application.controller.CartController;

import java.util.Objects;

public class CartView implements IView {

	private static final String VIEW_FORMAT = "|%3s|%12s|%13s|";

	private final CartController cartController;

	public CartView(final CartController cartController) {
		this.cartController = Objects.requireNonNull(cartController, "cartController is NULL!!");
	}

	@Override
	public void render() {
		System.out.println(String.format(VIEW_FORMAT, "ID", "PRODUCT_NAME", "PRODUCT_COUNT"));
		cartController.fetchCartItems()
				.forEach((key, value) -> System.out.println(String.format(VIEW_FORMAT,
						key.getId(),
						key.getName(),
						value.intValue())));
	}
}
