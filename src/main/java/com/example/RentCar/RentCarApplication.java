package com.example.RentCar;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(version = "1.0",
		title = "Rent Car",
		description = "All Operations"))
public class RentCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentCarApplication.class, args);
	}

}
