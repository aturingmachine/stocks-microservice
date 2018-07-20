package com.example.symbols.symbolsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SymbolsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SymbolsApiApplication.class, args);
	}
}
