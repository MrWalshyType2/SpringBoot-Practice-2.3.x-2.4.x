package com.example.products.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Product Model Class", value = "Product Model")
public class Product {

	@ApiModelProperty(dataType = "String", name = "Product ID", value = "1")
	private String id;
	
	@ApiModelProperty(dataType = "String", name = "Product Name", value = "Honey")
	private String name;
	
	public Product() {
		super();
	}
	
	public Product(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
