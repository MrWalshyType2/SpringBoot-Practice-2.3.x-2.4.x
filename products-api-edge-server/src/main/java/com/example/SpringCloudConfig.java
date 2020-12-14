package com.example;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
					  .route("product-api", request -> 
					  						request.path("/api/**").uri("lb://product-api"))
					  .route("configclientreq", request ->
						  					request.path("/config/message").uri("lb://product-api"))
//					  .route("product-actuator", request ->
//							  				request.path("/actuator/**").uri("lb://product-api"))
					  .build();
	}
}
