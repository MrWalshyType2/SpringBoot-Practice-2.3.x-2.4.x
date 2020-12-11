package com.example.products.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

	@Value("${welcome.message}")
	private String welcomeMessage;
	
	@GetMapping("/config/message")
	public ResponseEntity<String> welcome() {
		return new ResponseEntity<>(welcomeMessage, HttpStatus.OK);
	}
}
