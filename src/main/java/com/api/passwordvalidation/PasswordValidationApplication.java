package com.api.passwordvalidation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="Password Validation Service"))
public class PasswordValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordValidationApplication.class, args);
	}
}
