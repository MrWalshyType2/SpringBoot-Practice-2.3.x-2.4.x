package com.example.products.api.model;

public class SerializingExample {

	private String name;
	
	private Product product;
	
	public SerializingExample() {
		super();
		this.name = "";
		this.product = new Product();
	}
	
	public SerializingExample(String name, Product product) {
		super();
		this.name = name;
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
