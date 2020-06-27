package com.application.factory;

import com.application.model.product.Category;
import com.application.model.tax.ITax;
import com.application.model.tax.Percent;
import com.application.model.tax.SalesTax;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class creates List of taxes applicable on provided category.
 */

public class ProductCategoryTaxFactory {

	/**
	 * Provides applicable taxes on given product category.
	 *
	 * @param category product category
	 * @return list of taxes applicable
	 */
	public List<ITax> getTaxes(final Category category) {
		final List<ITax> taxes = new ArrayList<>();
		if (Objects.nonNull(category)) {
			switch (category) {
				case CATEGORY_A:
					taxes.add(new SalesTax(Percent.TEN_PERCENT));
					break;
				case CATEGORY_B:
					taxes.add(new SalesTax(Percent.TWENTY_PERCENT));
					break;
				case CATEGORY_C:
					taxes.add(new SalesTax(Percent.ZERO_PERCENT));
					break;
				default:
					break;
			}
		}
		return taxes;
	}

}
