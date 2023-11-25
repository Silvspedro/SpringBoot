package br.com.locadora.locacao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import br.com.locadora.locacao.entidade.Carro;
import br.com.locadora.locacao.exception.NegocioException;
import br.com.locadora.locacao.pojo.CarroPojo;
import br.com.locadora.locacao.repository.CarroRepository;

@Service
public class CarroService {

	private final CarroRepository carroRepository;

	public CarroService(CarroRepository carroRepository) {
		this.carroRepository = carroRepository;
	}

	public CarroPojo salvar(Carro carro) {
		validarCarros(carro);
		return new CarroPojo(carroRepository.save(carro));
	}

	private void validarCarros(Carro carro) {
		if (carro.getNome() == null || carro.getNome() == "") {
			throw new NegocioException("Nome do Carro é obrigatório");
		} else if (carro.getCategoria() == null || carro.getCategoria() == "") {
			throw new NegocioException("A categoria do Carro é obrigatória");
		} else if (carro.getValor() == null || carro.getValor() == "") {
			throw new NegocioException("O valor do Carro é obrigatório");
		} else if (carro.getValor() == "0") {
			throw new NegocioException("O valor do Carro não pode ser zero");
		}

		List<Carro> carroBd = carroRepository.findByNome(carro.getNome());
		if (carro != null && !carroBd.isEmpty()) {
			throw new NegocioException("Carro já cadastrado");
		}

	}
	
	public List<CarroPojo> recuperarCarros(){
		List<Carro> listCarro = carroRepository.findAll();
		List<CarroPojo> listCarroPojo = new ArrayList<>();
		for (Carro Carro : listCarro) {
			listCarroPojo.add(new CarroPojo(Carro));
		}
		return listCarroPojo;
	}
	
	public CarroPojo recuperarCarro(Long id) {
		Optional<Carro> CarroOptional = carroRepository.findById(id);
		if (CarroOptional.isPresent()) {
			return new CarroPojo(CarroOptional.get());
		}
		return null;
	}
	
	public void deletarCarro(Long id) {
		carroRepository.deleteById(id);
	}
}
