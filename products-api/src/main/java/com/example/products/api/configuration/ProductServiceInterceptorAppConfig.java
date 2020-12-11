package com.example.products.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.products.api.configuration.interceptors.ProductServiceInterceptor;

@Profile("dev")
@Configuration
public class ProductServiceInterceptorAppConfig implements WebMvcConfigurer {

	@Autowired
	ProductServiceInterceptor productServiceInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(productServiceInterceptor);
	}
}
