package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@SpringCloudApplication
public class ProductsApiEdgeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApiEdgeServerApplication.class, args);
	}

}
