package com.application.service;

import com.application.factory.ProductCategoryTaxFactory;
import com.application.model.checkout.BillLineItem;
import com.application.model.checkout.BillLineItemProductDetails;
import com.application.model.checkout.BillLineItemTaxDetails;
import com.application.model.price.Price;
import com.application.model.product.Product;
import com.application.model.tax.ITax;
import com.application.util.Calculator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class will be responsible for creating Bill line.
 */

public class BillLineItemCreationService {

	private final ProductCategoryTaxFactory productCategoryTaxFactory;
	private final TaxCalculationService taxCalculationService;

	public BillLineItemCreationService(
			final ProductCategoryTaxFactory productCategoryTaxFactory,
			final TaxCalculationService taxCalculationService) {
		this.productCategoryTaxFactory = Objects.requireNonNull(productCategoryTaxFactory,
				"productCategoryTaxFactory" + " is NULL!!");
		this.taxCalculationService = Objects.requireNonNull(taxCalculationService, "taxCalculationService is NULL!!");
	}

	/**
	 * Creates Bill line item for given product
	 *
	 * @param product product for line
	 * @param count   number of pieces of product
	 * @return BillLineItem
	 */
	public BillLineItem createBillLineItem(final Product product, final int count) {
		final List<ITax> taxes = productCategoryTaxFactory.getTaxes(product.getCategory());
		final List<BillLineItemTaxDetails> billLineItemTaxDetails = createBillLineItemTaxDetails(taxes, product, count);
		final BillLineItemProductDetails billLineItemProductDetails = createBillLineItemProductDetail(product, count);
		final double lineCost = Calculator.add(accumulateTaxes(billLineItemTaxDetails),
				Calculator.multiply(product.getPrice().getAmount(), count));
		return new BillLineItem(billLineItemProductDetails,
				billLineItemTaxDetails,
				new Price(lineCost, billLineItemProductDetails.getItemPrice().getCurrency()));
	}

	private double accumulateTaxes(final List<BillLineItemTaxDetails> billLineItemTaxDetails) {
		return billLineItemTaxDetails.stream()
				.mapToDouble(billLineItemTaxDetail -> billLineItemTaxDetail.getTaxAmount().getAmount())
				.sum();
	}

	private BillLineItemProductDetails createBillLineItemProductDetail(final Product product, final int count) {
		return new BillLineItemProductDetails(product.getId(), product.getName(), product.getPrice(), count);
	}

	private List<BillLineItemTaxDetails> createBillLineItemTaxDetails(
			final List<ITax> taxes, final Product product, final int count) {
		return taxes.stream().map(tax -> createBillLineItemTaxDetail(tax, product, count)).collect(Collectors.toList());
	}

	private BillLineItemTaxDetails createBillLineItemTaxDetail(final ITax tax, final Product product, final int count) {
		return new BillLineItemTaxDetails(tax,
				new Price(taxCalculationService.calculateTaxAmount(tax,
						Calculator.multiply(product.getPrice().getAmount(), count)), product.getPrice().getCurrency()));
	}
}
