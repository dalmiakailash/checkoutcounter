package com.application.model.price;

/**
 * Represents price informations.
 * <li>amount</li>
 * <li>currency</li>
 */
public final class Price {

	private final double amount;
	private final String currency;

	public Price(final double amount, final String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

	@Override
	public String toString() {
		return currency + " " + amount;
	}
}
