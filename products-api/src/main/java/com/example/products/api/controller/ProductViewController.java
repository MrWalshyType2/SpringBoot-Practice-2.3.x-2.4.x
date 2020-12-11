package com.example.products.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductViewController {

	@GetMapping
	public String viewProducts() {
		return "view-products";
	}
	
	@GetMapping("/add")
	public String addProducts() {
		return "add-products";
	}
}
