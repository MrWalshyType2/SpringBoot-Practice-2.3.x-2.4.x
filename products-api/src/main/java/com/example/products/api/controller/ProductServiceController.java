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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "Products Service Controller")
@RestController
@RequestMapping("/api/products")
public class ProductServiceController {
	
	@Autowired
	ProductServiceInterface productService;

	@ApiOperation(value = "Get All Products", notes = "${ProductServiceController.getAllProducts}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved all products"),
			@ApiResponse(code = 204, message = "No content available to retrieve")
	})
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
	
	@ApiOperation(value = "Get Product by ID", notes = "${ProductServiceController.getProductById}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved product by ID"),
			@ApiResponse(code = 404, message = "No product found with the given ID")
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@ApiParam(name = "Get Product By ID",
											   value = "ID of the product to be retrieved") 
									 @PathVariable(value = "id", required = true) String id) {
		try {
			Optional<Product> response = productService.getById(id);
			Product responseData = response.get();
			
			return new ResponseEntity<Product>(responseData, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<String>(nsee.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Create Product", notes = "${ProductServiceController.createProduct}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully created product"),
			@ApiResponse(code = 400, message = "Something went wrong creating the product")
	})
	@PostMapping
	public ResponseEntity<?> create(@ApiParam(name = "Create Product",
			   								  value = "Product details to be saved") 
									@RequestBody Product product) {
		try {
			productService.create(product);
			return new ResponseEntity<String>("Product created successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something went wrong.", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@ApiOperation(value = "Update Product", notes = "${ProductServiceController.updateProduct}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully updated the product"),
			@ApiResponse(code = 404, message = "No product available with the specified ID"),
			@ApiResponse(code = 500, message = "Something went wrong on the server")
	})
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@ApiParam(name = "Update Product",
			   								  value = "ID of the product to be updated")
									@PathVariable("id") String id,
									@ApiParam(name = "Update Product",
	   								  		  value = "Product details to be saved") 
									@RequestBody Product product) {
		try {
			productService.update(id, product);
			return new ResponseEntity<String>("Product updated successfully!", HttpStatus.OK);
		} catch (ProductNotFoundException pnfe) {
			return new ResponseEntity<String>(pnfe.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Delete Product", notes = "${ProductServiceController.deleteProduct}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved all products"),
			@ApiResponse(code = 404, message = "No content available to retrieve"),
			@ApiResponse(code = 500, message = "Something went wrong on the server")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@ApiParam(name = "Delete Product",
				  							  value = "ID of the product to be deleted")
									@PathVariable("id") String id) {
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
