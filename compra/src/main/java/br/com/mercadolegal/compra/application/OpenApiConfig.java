package br.com.mercadolegal.compra.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI custonOpenAPI() {
		return new OpenAPI().info(new Info().title("Compras").description("API de compras online").version("1.0.0"));
	}
	
	
	
}
