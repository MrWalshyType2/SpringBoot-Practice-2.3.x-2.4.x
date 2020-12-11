package com.example.products.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.products.api.model.Product;
import com.example.products.api.model.SerializingExample;

@Validated
@RestController
@RequestMapping("/serializing")
public class SerializingExampleController {

	private List<SerializingExample> exampleRepo = new ArrayList<SerializingExample>(Arrays.asList(
			new SerializingExample("Bob", new Product("1", "Green Eggs")),
			new SerializingExample("Gregg", new Product("2", "Green Ham"))
			));
	
	@GetMapping
	public ResponseEntity<Collection<SerializingExample>> getAll() {
		return new ResponseEntity<Collection<SerializingExample>>(exampleRepo, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SerializingExample> getById(@PathVariable("id") String id) {
		return new ResponseEntity<SerializingExample>(exampleRepo.get(Integer.parseInt(id)), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> create(@NonNull @RequestBody SerializingExample se) {
		try {
			exampleRepo.add(se);
			return new ResponseEntity<String>("Created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
