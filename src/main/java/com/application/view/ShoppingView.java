package com.application.view;

import com.application.ApplicationContext;
import com.application.controller.CartController;
import com.application.exception.ProductNotFoundException;
import com.application.exception.ProductShortageException;
import com.application.model.view.View;

import java.util.Objects;
import java.util.Scanner;

public class ShoppingView implements IView {

	private final CartController cartController;

	public ShoppingView(
			final CartController cartController) {
		this.cartController = Objects.requireNonNull(cartController, "cartController is NULL!!");
	}

	@Override
	public void render() {
		ApplicationContext.getApplicationContext().getView(View.INVENTORY).render();
		final Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("Please select from following:");
			System.out.println("1.Add to cart");
			System.out.println("2.Remove from cart");
			System.out.println("3.Show cart");
			System.out.println("4.Check items in store");
			System.out.println("5.Checkout");
			System.out.println("6.Exit");
			System.out.println("Please enter your choice:");
			final int choice = scanner.nextInt();
			switch (choice) {
				case 1:
					addToCart(scanner);
					break;
				case 2:
					removeFromCart(scanner);
					break;
				case 3:
					ApplicationContext.getApplicationContext().getView(View.CART).render();
					break;
				case 4:
					ApplicationContext.getApplicationContext().getView(View.INVENTORY).render();
					break;
				case 5:
					ApplicationContext.getApplicationContext().getView(View.CHECKOUT).render();
					break;
				case 6:
					ApplicationContext.getApplicationContext().getView(View.EXIT).render();
				default:
					break;

			}
		}

	}

	private void removeFromCart(final Scanner scanner) {
		int productId = -1;
		int count = -1;
		while (productId < 0 && count < 0) {
			System.out.println("Enter product id:");
			productId = scanner.nextInt();
			System.out.println("Enter count:");
			count = scanner.nextInt();
		}
		cartController.removeFromCart(productId, count);
	}

	private void addToCart(final Scanner scanner) {
		int productId = -1;
		int count = -1;
		do{
			System.out.println("Enter product id:");
			productId = scanner.nextInt();
			System.out.println("Enter count:");
			count = scanner.nextInt();
			if(productId > 0 && count > 0) {
				try {
					cartController.addToCart(productId, count);
					System.out.println("Added to cart.");
				} catch (final ProductNotFoundException | ProductShortageException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("Please enter valid details.");
			}
			System.out.println("Press C to continue to add to cart, Q to quit.");
		}while ("C".equalsIgnoreCase(scanner.next()));
	}
}
