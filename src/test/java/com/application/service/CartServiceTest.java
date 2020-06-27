package com.application.service;

import com.application.exception.ProductNotFoundException;
import com.application.exception.ProductShortageException;
import com.application.model.cart.Cart;
import com.application.model.price.Price;
import com.application.model.product.Category;
import com.application.model.product.Product;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class CartServiceTest {

	@Captor
	private ArgumentCaptor<Product> productArgumentCaptor;

	@Captor
	private ArgumentCaptor<Integer> integerArgumentCaptor;

	@Mock
	private InventoryService inventoryService;

	@InjectMocks
	private CartService cartService;

	@After
	public void tearDown(){
		cartService.resetCart();
	}

	@Test
	public void shouldBeAbleToAddInCart() throws ProductNotFoundException, ProductShortageException {
		//given
		final Product product = new Product(1, "A", new Price(10, "INR"), Category.CATEGORY_A);

		//when
		when(inventoryService.checkAvailability(anyInt(), anyInt())).thenReturn(true);
		when(inventoryService.fetchById(anyInt())).thenReturn(Optional.of(product));
		doNothing().when(inventoryService).remove(productArgumentCaptor.capture(), integerArgumentCaptor.capture());

		//then
		cartService.add(1, 1);

		assertNotNull(cartService.fetchCartItems());
		assertEquals(1, cartService.fetchCartItems().size());
		assertNotNull(cartService.fetchCartItems().get(product));
		assertEquals(1, cartService.fetchCartItems().get(product).intValue());

		verify(inventoryService, times(1)).checkAvailability(1, 1);
		verify(inventoryService, times(1)).fetchById(1);
		verify(inventoryService, times(1)).remove(productArgumentCaptor.getValue(), integerArgumentCaptor.getValue());

		assertEquals(product, productArgumentCaptor.getValue());
		assertEquals(1, integerArgumentCaptor.getValue(), 0);
	}

	@Test (expected = ProductNotFoundException.class)
	public void throwProductNotAvailable_whenProductForAddNotAvailable() throws
			ProductNotFoundException,
			ProductShortageException {
		//when
		when(inventoryService.checkAvailability(anyInt(), anyInt())).thenReturn(false);
		when(inventoryService.checkAvailability(anyInt())).thenReturn(false);

		//then
		cartService.add(1, 1);
	}

	@Test (expected = ProductShortageException.class)
	public void throwProductNotAvailable_whenProductForAddNotAvailableInRequestedQantity() throws
			ProductNotFoundException,
			ProductShortageException {
		//when
		when(inventoryService.checkAvailability(anyInt(), anyInt())).thenReturn(false);
		when(inventoryService.checkAvailability(anyInt())).thenReturn(true);

		//then
		cartService.add(1, 1);
	}

	@Test
	public void shouldBeAbleToRemoveFromCart() {
		//given
		final Product product = new Product(1, "A", new Price(10, "INR"), Category.CATEGORY_A);
		Cart.getInstance().addToCart(product, 1);

		//when
		doNothing().when(inventoryService).add(productArgumentCaptor.capture(), integerArgumentCaptor.capture());

		//then
		cartService.remove(1, 1);

		assertNotNull(cartService.fetchCartItems());
		assertEquals(0, cartService.fetchCartItems().size());
		assertNull(cartService.fetchCartItems().get(product));

		verify(inventoryService, times(1)).add(productArgumentCaptor.getValue(), integerArgumentCaptor.getValue());

		assertEquals(product, productArgumentCaptor.getValue());
		assertEquals(1, integerArgumentCaptor.getValue(), 0);
	}

	@Test
	public void removalShouldNotFailIfRequestedItemIsNotInCart() {
		//given
		final Product product = new Product(1, "A", new Price(10, "INR"), Category.CATEGORY_A);

		//then
		cartService.remove(1, 1);

		assertNotNull(cartService.fetchCartItems());
		assertEquals(0, cartService.fetchCartItems().size());
		assertNull(cartService.fetchCartItems().get(product));

		verify(inventoryService, times(0)).add(any(Product.class), anyInt());
	}

}
