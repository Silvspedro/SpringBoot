package br.com.locadora.locacao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.locadora.locacao.entidade.Cliente;
import br.com.locadora.locacao.pojo.ClientePojo;
import br.com.locadora.locacao.repository.ClienteRepository;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteRepository clienteRepository;
	
	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@GetMapping
	public List<ClientePojo> getAll() {
		List<Cliente> listCliente = clienteRepository.findAll();
		List<ClientePojo> listClientePojo = new ArrayList<>();
		for (Cliente cliente : listCliente) {
			listClientePojo.add(new ClientePojo(cliente));
		}
		return listClientePojo;
	}

	@GetMapping(path = "/{id}")
	public ClientePojo get(@PathVariable Long id) {
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		if (clienteOptional.isPresent()) {
			return new ClientePojo(clienteOptional.get());
		}
		return new ClientePojo();
	}
	
	@PostMapping
	public ClientePojo create(@RequestBody ClientePojo clientePojo) {

		return new ClientePojo(clienteRepository.save(clientePojo.toEntity()));

	}
	
	@PutMapping
	public ClientePojo update(@RequestBody ClientePojo clientePojo) {

		return new ClientePojo(clienteRepository.save(clientePojo.toEntity()));

	}

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable Long id) {
		clienteRepository.deleteById(id);

	}
	
}
