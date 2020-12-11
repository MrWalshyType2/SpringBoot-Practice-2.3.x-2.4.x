package com.example.products.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WebMvcController {

	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping("/locale")
	public String locale() {
		return "locale";
	}
}
