package br.com.locadora.locacao.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.locadora.locacao.pojo.CarroPojo;
import br.com.locadora.locacao.exception.NegocioException;
import br.com.locadora.locacao.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/carros")
public class CarroController {
	private final CarroService carroService;

	public CarroController( CarroService carroService) {
		this.carroService = carroService;
	}

	@GetMapping
	@Operation(summary = "Recupera Todos os Carros", description = "Recupera todos os Carros cadastrados")
	public ResponseEntity<List<CarroPojo>> getAll() {
		return ResponseEntity.ok(carroService.recuperarCarros());
	}

	@GetMapping(path = "/{id}")
	@Operation(summary = "Recupera Carros por ID", description = "Recupera Carros especificos cadastrados")
	public ResponseEntity<CarroPojo> get(@PathVariable Long id) {
		CarroPojo carroPojo = carroService.recuperarCarro(id);
		if (carroPojo == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(carroPojo);
	}

	@PostMapping
	@Operation(summary = "Cria novos Carros", description = "Cria novos Carros para inserção no sistema")
	public ResponseEntity<?> create(@RequestBody CarroPojo carroPojo) {
		try {
			return new ResponseEntity<CarroPojo>(carroService.salvar(carroPojo.toEntity()), HttpStatus.CREATED);
		} catch (NegocioException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
		}
	}

	@PutMapping
	@Operation(summary = "Altera Carros", description = "Altera algum requisito de Carros já cadastrados")
	public ResponseEntity<?> update(@RequestBody CarroPojo carroPojo) {
		try {
			return ResponseEntity.ok(carroService.salvar(carroPojo.toEntity()));
		} catch (NegocioException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
		}
	}

	@DeleteMapping(path = "/{id}")
	@Operation(summary = "Deleta Carros", description = "Deleta Carros excluindo os seus cadastrados")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		carroService.deletarCarro(id);
		return ResponseEntity.ok().build();
	}

}