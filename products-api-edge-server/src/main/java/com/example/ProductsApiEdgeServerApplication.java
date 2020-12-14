package com.example;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.vavr.CheckedFunction1;

@SpringBootApplication
@EnableDiscoveryClient
//@SpringCloudApplication
public class ProductsApiEdgeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApiEdgeServerApplication.class, args);
	}

}

@RestController
class FailingController {
	private final FailingService failingService;

	public FailingController(FailingService failingService) {
		super();
		this.failingService = failingService;
	}
	
	@GetMapping("/greeting")
	String greet(@RequestParam(required = false) String name) {
		return this.failingService.greet(name);
	}
}

@Service
class FailingService {
	
    private CircuitBreaker circuitBreaker;

//	Supplier<?> greet(Supplier<String> name) {
////		if (!name.equals(null)) return new Supplier(name.get());
////		throw new NullPointerException();
//	}
	public FailingService(CircuitBreakerFactory cbf) {
		this.circuitBreaker = cbf.create("greet");
	}
	
	public String greet(String name) { 
			return circuitBreaker.run(() -> {
					if (!name.equals(null)) return "Welcome " + name + "!";
					throw new NullPointerException();
				}, throwable -> defaultGreeting());
	}
	
	public String defaultGreeting() {
		return "Welcome user";
	}
}
