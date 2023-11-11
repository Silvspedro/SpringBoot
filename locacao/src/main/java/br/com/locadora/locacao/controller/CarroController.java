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

import br.com.locadora.locacao.entidade.Carro;
import br.com.locadora.locacao.pojo.CarroPojo;
import br.com.locadora.locacao.repository.CarroRepository;

@RestController
@RequestMapping("/carros")
public class CarroController {

private final CarroRepository carroRepository;
	
	public CarroController(CarroRepository carroRepository) {
		this.carroRepository = carroRepository;
	}
	
	@GetMapping
	public List<CarroPojo> getAll() {
		List<Carro> listCarro = carroRepository.findAll();
		List<CarroPojo> listCarroPojo = new ArrayList<>();
		for (Carro carro : listCarro) {
			listCarroPojo.add(new CarroPojo(carro));
		}
		return listCarroPojo;
	}
	
	@GetMapping(path = "/{id}")
	public CarroPojo get(@PathVariable Long id) {
		Optional<Carro> carroOptional = carroRepository.findById(id);
		if (carroOptional.isPresent()) {
			return new CarroPojo(carroOptional.get());
		}
		return new CarroPojo();
	}
	
	@PostMapping
	public CarroPojo create(@RequestBody CarroPojo carroPojo) {
		return new CarroPojo(carroRepository.save(carroPojo.toEntity()));
	}

	@PutMapping
	public CarroPojo update(@RequestBody CarroPojo carroPojo) {
		return new CarroPojo(carroRepository.save(carroPojo.toEntity()));
	}

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable Long id) {
		carroRepository.deleteById(id);
	}
	
}
