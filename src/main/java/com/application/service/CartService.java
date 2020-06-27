package com.application.service;

import com.application.exception.ProductNotFoundException;
import com.application.exception.ProductShortageException;
import com.application.model.cart.Cart;
import com.application.model.cart.CartItem;
import com.application.model.product.Product;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * This class is responsible for servicing a shopping cart.
 * Operations served are:
 * <li>fetch cart items</li>
 * <li>add to cart</li>
 * <li>remove from cart</li>
 * <li>discard/clear cart</li>
 */

public class CartService {

	private final Cart cart = Cart.getInstance();

	private final InventoryService inventoryService;

	public CartService(final InventoryService inventoryService) {
		this.inventoryService = Objects.requireNonNull(inventoryService, "inventoryService is NULL!!");
	}

	/**
	 * Fetches all items available in cart.
	 *
	 * @return List of {@link CartItem}
	 */
	public Map<Product, BigInteger> fetchCartItems() {
		return cart.getCartItems();
	}

	/**
	 * Removed provided cart item from the cart.
	 * Complete cart item will be removed. Its is as good as removing item from cart(not decreasing the count)
	 *
	 * @param productId Item to be deleted
	 * @param count     number of items to be removed
	 */
	public void remove(final long productId, final int count) {
		final Optional<Product> product = fetchProduct(productId);
		if (product.isPresent()) {
			cart.removeFromCart(product.get(), count);
			inventoryService.add(product.get(), count);
		}
	}

	/**
	 * Removed all items of given product
	 *
	 * @param productId Item to be deleted
	 */
	public void removeAll(final long productId) {
		final Optional<Product> product = fetchProduct(productId);
		if (product.isPresent()) {
			final int count = cart.getCartItems().get(product.get()).intValue();
			cart.removeFromCart(product.get());
			inventoryService.add(product.get(), count);
		}
	}

	/**
	 * Adds provided product to cart
	 *
	 * @param productId product id to be added
	 * @param count     quantity of product to be added
	 */
	public void add(final long productId, final int count) throws ProductNotFoundException, ProductShortageException {
		if (inventoryService.checkAvailability(productId, count)) {
			final Product product = inventoryService.fetchById(productId).get();
			cart.addToCart(product, count);
			inventoryService.remove(product, count);
		} else if (inventoryService.checkAvailability(productId)) {
			throw new ProductShortageException("Please select less than "
					+ inventoryService.availableCount(productId)
					+ "pieces");
		} else {
			throw new ProductNotFoundException("Searched product not found in inventory.");
		}

	}

	/**
	 * Resets complete cart.
	 * With this operation Inventory will be affected.
	 */
	public void resetCart() {
		new HashMap<>(fetchCartItems()).keySet().forEach(cartItem -> removeAll(cartItem.getId()));
	}

	/**
	 * Checks if cart if empty or full
	 *
	 * @return false if empty
	 */
	public boolean isNotEmpty() {
		return !cart.getCartItems().isEmpty();
	}

	private Optional<Product> fetchProduct(final long productId) {
		final Optional<Product> product = fetchCartItems().keySet()
				.stream()
				.filter(cartItem -> cartItem.getId() == productId)
				.findFirst();
		return product;
	}
}
