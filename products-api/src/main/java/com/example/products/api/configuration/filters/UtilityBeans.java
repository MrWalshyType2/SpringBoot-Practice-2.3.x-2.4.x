package com.example.products.api.configuration.filters;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UtilityBeans {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
