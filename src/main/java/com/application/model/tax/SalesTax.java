package com.application.model.tax;

import java.util.Objects;

/**
 * This class represents sales tax applicable on product.
 */
public final class SalesTax implements ITax {

	private final Percent percent;

	public SalesTax(final Percent percent) {
		this.percent = Objects.requireNonNull(percent, "percent is NULL!!");
	}

	@Override
	public Percent getPercentage() {
		return percent;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof SalesTax)) {
			return false;
		}
		final SalesTax salesTax = (SalesTax) o;
		return Objects.equals(percent, salesTax.percent);
	}

	@Override
	public int hashCode() {
		return Objects.hash(percent);
	}
}
