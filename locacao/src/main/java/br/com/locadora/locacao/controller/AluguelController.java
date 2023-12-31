package br.com.locadora.locacao.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.locadora.locacao.entidade.Aluguel;
import br.com.locadora.locacao.pojo.AluguelPojo;
import br.com.locadora.locacao.repository.AluguelRepository;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {
	
private final AluguelRepository aluguelRepository;
	
	public AluguelController(AluguelRepository aluguelRepository) {
		this.aluguelRepository = aluguelRepository;
	}
	
	@PostMapping
	@Operation(summary = "Cria novos Alugueis", description = "Cria novos Alugueis para inserção no sistema")
	public AluguelPojo create(@RequestBody AluguelPojo aluguelPojo) {
		return new AluguelPojo(aluguelRepository.save(aluguelPojo.toEntity()));
	}

	@PutMapping
	@Operation(summary = "Altera Alugueis", description = "Altera algum Alugueis de Usuarios já cadastrados")
	public AluguelPojo update(@RequestBody AluguelPojo aluguelPojo) {
		return new AluguelPojo(aluguelRepository.save(aluguelPojo.toEntity()));
	}
	@DeleteMapping(path = "/{id}")
	@Operation(summary = "Deleta Alugueis", description = "Deleta Alugueis excluindo os seus cadastrados")
	public void delete(@PathVariable Long id) {
		aluguelRepository.deleteById(id);
	}

	@GetMapping(path = "/{id}")
	@Operation(summary = "Recupera Alugueis por ID", description = "Recupera Alugueis especificos cadastrados")
	public AluguelPojo get(@PathVariable Long id) {
		Optional<Aluguel> aluguelOptional = aluguelRepository.findById(id);
		if (aluguelOptional.isPresent()) {
			return new AluguelPojo(aluguelOptional.get());
		}else {
			return new AluguelPojo();
		}
	}
	@GetMapping
	@Operation(summary = "Recupera Todos Alugueis", description = "Recupera todos os Alugueis cadastrados")
	public List<AluguelPojo> get(){
		return aluguelRepository.findAll().stream().map(AluguelPojo:: new).collect(Collectors.toList());
	}

}
