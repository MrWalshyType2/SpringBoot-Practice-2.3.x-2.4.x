package com.example.products.api.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.products.api.exception.ProductNotFoundException;
import com.example.products.api.model.Product;
import com.example.products.api.model.Products;
import com.example.products.api.service.interfaces.ProductServiceInterface;

@Service
public class ProductService implements ProductServiceInterface {

	private static Map<String, Product> productRepo = new HashMap<>();
	
	static {
		Product honey = new Product();
		honey.setId("1");
		honey.setName("Honey");
		productRepo.put(honey.getId(), honey);
		  
		Product almond = new Product();
		almond.setId("2");
		almond.setName("Almond");
		productRepo.put(almond.getId(), almond);
	}

	@Override
	public void create(Product product) {
		productRepo.put(product.getId(), product);
	}

	@Override
	public Optional<Products> getAll() {
		Products products = new Products(productRepo.values());
		return Optional.of(products);
	}

	@Override
	public Optional<Product> getById(String id) {
		if (!productRepo.containsKey(id)) throw new ProductNotFoundException();
		return Optional.of(productRepo.get(id));
	}

	@Override
	public void update(String id, Product product) {
		if (!productRepo.containsKey(id)) throw new ProductNotFoundException();
		
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
	}

	@Override
	public void delete(String id) {
		if (!productRepo.containsKey(id)) throw new ProductNotFoundException();
		
		productRepo.remove(id);
	}
}
