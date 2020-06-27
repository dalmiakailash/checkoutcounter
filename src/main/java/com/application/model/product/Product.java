package com.application.model.product;

import com.application.model.price.Price;

import java.util.Objects;

/**
 * Representation of a product. It consist of:
 * <li>product price</li>
 * <li>product {@link Category}</li>
 * <li>name of product</li>
 * <li>id of product</li>
 */
public final class Product {

	private final long id;
	private final String name;
	private final Price price;
	private final Category category;

	public Product(final long id, final String name, final Price price, final Category category) {
		this.id = id;
		this.name = Objects.requireNonNull(name, "name is NULL!!");
		this.price = price;
		this.category = Objects.requireNonNull(category, "category is NULL!!");
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Price getPrice() {
		return price;
	}

	public Category getCategory() {
		return category;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Product)) {
			return false;
		}
		final Product product = (Product) o;
		return getId() == product.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return "Product{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
	}
}
