package com.application.exception;

/**
 * Custom exception when product is not found in inventory.
 */
public class ProductNotFoundException extends Exception {

	public ProductNotFoundException(final String message) {
		super(message);
	}

}
