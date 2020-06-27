package com.application.exception;

/**
 * Exception when requested amount of product is not available in inventory.
 */
public class ProductShortageException extends Exception {

	public ProductShortageException(final String message) {
		super(message);
	}
}
