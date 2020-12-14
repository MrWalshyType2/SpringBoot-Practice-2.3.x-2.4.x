package com.example.products.api.configuration;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	// Docket is used for building the swagger config
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.example.products.api"))
//				.paths(PathSelectors.any()).build()
//				.pathMapping("/")
//				.directModelSubstitute(LocalDate.class, Date.class)
//				.genericModelSubstitutes(ResponseEntity.class)
//				.useDefaultResponseMessages(false)
//				.apiInfo(apiInfo())
//				.enableUrlTemplating(true);
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.products.api.controller"))
				.paths(PathSelectors.regex("/api.*"))
				.build();
	}
	
	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.description("Product Service List")
				.title("Product Service")
				.license("Products")
				.licenseUrl("products")
				.contact(contact())
				.build();
	}
	
	@Bean
	public Contact contact() {
		return new Contact("Morgan Walsh", "url.com", "mwalsh@email.com");
	}
}
