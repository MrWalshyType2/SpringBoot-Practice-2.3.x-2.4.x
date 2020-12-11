package com.example.products.api.controller;

import java.net.URI;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.products.api.model.Product;

@RestController
@RequestMapping("/template")
public class ProductServiceConsumerController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/products")
	public ResponseEntity<String> getProductList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
														"http://localhost:443/api/products",
														HttpMethod.GET,
														entity,
														String.class);
		
		return new ResponseEntity<String>(response.getBody(), response.getStatusCode());
	}
	
	@PostMapping("/products")
	public ResponseEntity<String> createProducts(@RequestBody Product product) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
														"http://localhost:443/api/products",
														HttpMethod.POST,
														entity,
														String.class);
		
		return new ResponseEntity<String>(response.getBody(), response.getStatusCode());
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
														"http://localhost:443/api/products/" + id,
														HttpMethod.PUT,
														entity,
														String.class);
		
		return new ResponseEntity<String>(response.getBody(), response.getStatusCode());
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
														"http://localhost:443/api/products/" + id,
														HttpMethod.DELETE,
														entity,
														String.class);
		
		return new ResponseEntity<String>(response.getBody(), response.getStatusCode());
	}
}
