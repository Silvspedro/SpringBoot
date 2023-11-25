package br.com.locadora.locacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API de Aluguel",
				description = "API de Aluguel de carros online",
				version = "v1.0"			
				)		
		)
public class LocacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocacaoApplication.class, args);
	}

}
