package com.application.factory;

import com.application.model.product.Category;
import com.application.model.tax.ITax;
import com.application.model.tax.Percent;
import com.application.model.tax.SalesTax;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ProductCategoryTaxFactoryTest {

	private final ProductCategoryTaxFactory productCategoryTaxFactory = new ProductCategoryTaxFactory();

	@Test
	public void shouldBeAbleToIdentifyCategoryA() {
		//given
		final Category category = Category.CATEGORY_A;
		final List<ITax> expected = Arrays.asList(new SalesTax(Percent.TEN_PERCENT));

		//then
		final List<ITax> actual = productCategoryTaxFactory.getTaxes(category);
		assertEquals(expected, actual);

	}

	@Test
	public void shouldBeAbleToIdentifyCategoryB() {
		//given
		final Category category = Category.CATEGORY_B;
		final List<ITax> expected = Arrays.asList(new SalesTax(Percent.TWENTY_PERCENT));

		//then
		final List<ITax> actual = productCategoryTaxFactory.getTaxes(category);
		assertEquals(expected, actual);

	}

	@Test
	public void shouldBeAbleToIdentifyCategoryC() {
		//given
		final Category category = Category.CATEGORY_C;
		final List<ITax> expected = Arrays.asList(new SalesTax(Percent.ZERO_PERCENT));

		//then
		final List<ITax> actual = productCategoryTaxFactory.getTaxes(category);
		assertEquals(expected, actual);

	}

	@Test
	public void shouldBeAbleToHandleUndefinedCategory() {
		//given
		final List<ITax> expected = new ArrayList<>();

		//then
		final List<ITax> actual = productCategoryTaxFactory.getTaxes(null);
		assertEquals(expected, actual);

	}

}
