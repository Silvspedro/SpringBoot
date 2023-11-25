package br.com.locadora.locacao.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.locadora.locacao.entidade.Pagamento;
import br.com.locadora.locacao.pojo.PagamentoPojo;
import br.com.locadora.locacao.repository.PagamentoRepository;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	private final PagamentoRepository pagamentoRepository;

	public PagamentoController(PagamentoRepository pagamentoRepository) {
		this.pagamentoRepository = pagamentoRepository;
	}
	
	@PostMapping
	@Operation(summary = "Cria novos Pagamentos", description = "Cria novos Pagamentos para inserção no sistema")
	public PagamentoPojo create(@RequestBody PagamentoPojo pagamentoPojo) {
		return new PagamentoPojo(pagamentoRepository.save(pagamentoPojo.toEntity()));
	}

	@PutMapping
	@Operation(summary = "Altera Pagamentos", description = "Altera algum Erro feito ao realizar Pagamentos")
	public PagamentoPojo update(@RequestBody PagamentoPojo pagamentoPojo) {
		return new PagamentoPojo(pagamentoRepository.save(pagamentoPojo.toEntity()));
	}
	
	@GetMapping(path = "/{id}")
	@Operation(summary = "Recupera Pagamentos por ID", description = "Recupera Pagamentos realizados por ID")
	public PagamentoPojo get(@PathVariable Long id) {
		Optional<Pagamento> pagamentoOptional = pagamentoRepository.findById(id);
		if (pagamentoOptional.isPresent()) {
			return new PagamentoPojo(pagamentoOptional.get());
		}else {
			return new PagamentoPojo();
		}
	}
	@GetMapping
	@Operation(summary = "Recupera Todos Pagamentos", description = "Recupera todos os Pagamentos ja realizados")
	public List<PagamentoPojo> get(){
		
		return pagamentoRepository.findAll().stream().map(PagamentoPojo:: new).collect(Collectors.toList());
	}
	
	
}
