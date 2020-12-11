package com.example.products.api.model;

import java.util.Collection;

public class Products {

	private Collection<Product> products;
	
	public Products() {
		super();
	}

	public Products(Collection<Product> products) {
		super();
		this.products = products;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
}
