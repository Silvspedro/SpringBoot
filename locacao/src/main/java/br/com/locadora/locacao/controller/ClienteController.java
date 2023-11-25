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

import br.com.locadora.locacao.exception.NegocioException;
import br.com.locadora.locacao.pojo.ClientePojo;
import br.com.locadora.locacao.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping
	@Operation(summary = "Recupera Todos usuarios", description = "Recupera todos os Usuarios cadastrados")
	public ResponseEntity<List<ClientePojo>> getAll() {
		return ResponseEntity.ok(clienteService.recuperarClientes());
	}

	@GetMapping(path = "/{id}")
	@Operation(summary = "Recupera usuarios por ID", description = "Recupera Usuarios especificos cadastrados")
	public ResponseEntity<ClientePojo> get(@PathVariable Long id) {
		ClientePojo clientePojo = clienteService.recuperarCliente(id);
		if (clientePojo == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clientePojo);
	}

	@PostMapping
	@Operation(summary = "Cria novos usuarios", description = "Cria novos usuarios para inserção no sistema")
	public ResponseEntity<?> create(@RequestBody ClientePojo clientePojo) {
		try {
			return new ResponseEntity<ClientePojo>(clienteService.salvar(clientePojo.toEntity()), HttpStatus.CREATED);
		} catch (NegocioException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
		}
	}

	@PutMapping
	@Operation(summary = "Altera usuarios", description = "Altera algum requisito de Usuarios já cadastrados")
	public ResponseEntity<?> update(@RequestBody ClientePojo clientePojo) {
		try {
			return ResponseEntity.ok(clienteService.salvar(clientePojo.toEntity()));
		} catch (NegocioException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
		}
	}

	@DeleteMapping(path = "/{id}")
	@Operation(summary = "Deleta usuarios", description = "Deleta Usuarios excluindo os seus cadastrados")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		clienteService.deletarCliente(id);
		return ResponseEntity.ok().build();
	}

}
