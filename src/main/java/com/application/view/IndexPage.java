package com.application.view;

import com.application.ApplicationContext;
import com.application.model.view.View;

import java.util.Objects;
import java.util.Scanner;

/**
 * Landing page/view of application.
 */
public class IndexPage implements IView {

	@Override
	public void render() {
		while (true) {
			System.out.println("Please select from menu:");
			System.out.println("1.Shop");
			System.out.println("2.Cart");
			System.out.println("3.Checkout");
			System.out.println("4.Exit");
			System.out.println("Please enter your choice:");
			final int selection = new Scanner(System.in).nextInt();
			final IView view;
			switch (selection) {
				case 1:
					view = ApplicationContext.getApplicationContext().getView(View.SHOPPING);
					break;
				case 2:
					view = ApplicationContext.getApplicationContext().getView(View.CART);
					break;
				case 3:
					view = ApplicationContext.getApplicationContext().getView(View.CHECKOUT);
					break;
				case 4:
					view = ApplicationContext.getApplicationContext().getView(View.EXIT);
					break;
				default:
					view = null;
					break;
			}
			if (Objects.nonNull(view)) {
				view.render();
			} else {
				System.out.println("Please make a valid choice.");
			}
		}

	}
}
