package com.example.products.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/products")
			.allowedHeaders("*")
			.allowedMethods("*")
			.allowedOrigins("http://localhost:8080", "http://localhost:9090", "https://localhost", "http://localhost:443", "https://localhost:443");
		registry
			.addMapping("/api/**")
			.allowedHeaders("*")
			.allowedMethods("*")
			//.exposedHeaders("Access-Control-Allow-Origin")
			.allowedOrigins("http://localhost:8080", "http://localhost:9090", "https://localhost", "http://localhost:443", "https://localhost:443");
	}
}
