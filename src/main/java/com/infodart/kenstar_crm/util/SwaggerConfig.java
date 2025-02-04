package com.infodart.kenstar_crm.util;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/*import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;*/

/*@Configuration
public class SwaggerConfig {

	
	 

	@Bean
	public OpenAPI defineOpenApi() {
		Server server = new Server();
		server.setUrl("http://localhost:8080");
		server.setDescription("Development");

		Contact myContact = new Contact();
		myContact.setName("Jane Doe");
		myContact.setEmail("your.email@gmail.com");

		Info information = new Info().title("Employee Management System API").version("1.0")
				.description("This API exposes endpoints to manage employees.");//.contact(myContact);
		return new OpenAPI().info(information);//.servers(List.of(server));
	}
}*/

  // Marks this class as a configuration class
//@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer") 
// Configures
																												// security
																												// scheme
																												// for // JWT
@Configuration																												
public class SwaggerConfig {

	@Bean // Declares this method as a Spring Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components()) // Initializes OpenAPI components
				.info(new Info().title("Sample Swagger API Documentation") // Sets the API title
						.description("This document provides API details for a sample Spring Boot Project")); // Sets
																												// the
																												// API
																												// description
	}
}
