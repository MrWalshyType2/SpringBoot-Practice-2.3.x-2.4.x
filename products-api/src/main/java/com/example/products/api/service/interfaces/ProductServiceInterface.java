package com.example.products.api.service.interfaces;

import java.util.Optional;

import com.example.products.api.model.Product;
import com.example.products.api.model.Products;

public interface ProductServiceInterface {
	public void create(Product product);
	public Optional<Products> getAll();
	public Optional<Product> getById(String id);
	public void update(String id, Product product);
	public void delete(String id);
}
