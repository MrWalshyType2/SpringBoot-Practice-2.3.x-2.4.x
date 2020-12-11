package com.example.products.api.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.products.api.exception.ProductNotFoundException;
import com.example.products.api.model.Product;
import com.example.products.api.model.Products;
import com.example.products.api.service.interfaces.ProductServiceInterface;

@RestController
@RequestMapping("/api/products")
public class ProductServiceController {
	
	@Autowired
	ProductServiceInterface productService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		try {
			Optional<Products> response = productService.getAll();
			Products responseData = response.get();
			
			return new ResponseEntity<Products>(responseData, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<String>(nsee.getMessage(), HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(value = "id", required = true) String id) {
		try {
			Optional<Product> response = productService.getById(id);
			Product responseData = response.get();
			
			return new ResponseEntity<Product>(responseData, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<String>(nsee.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Product product) {
		try {
			productService.create(product);
			return new ResponseEntity<String>("Product created successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something went wrong.", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Product product) {
		try {
			productService.update(id, product);
			return new ResponseEntity<String>("Product updated successfully!", HttpStatus.OK);
		} catch (ProductNotFoundException pnfe) {
			return new ResponseEntity<String>(pnfe.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		try {
			productService.delete(id);
			return new ResponseEntity<String>("Product deleted successfully!", HttpStatus.OK);
		} catch (ProductNotFoundException pnfe) {
			return new ResponseEntity<String>(pnfe.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
