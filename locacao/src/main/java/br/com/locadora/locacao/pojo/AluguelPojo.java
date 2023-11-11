package br.com.locadora.locacao.pojo;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.locadora.locacao.entidade.Aluguel;


public class AluguelPojo {

	private Long id;
	
	private ClientePojo cliente;
	
	private List<CarroPojo> carros;
	
	public AluguelPojo() {}
	
	public AluguelPojo (Aluguel aluguel) {
		this.id = aluguel.getId();
		this.cliente = new ClientePojo(aluguel.getCliente());
		//transforma um tipo em outro para evitar reescrita de codigo
		this.carros = aluguel.getCarros().stream().map(CarroPojo:: new).collect(Collectors.toList());
		
	} 
	@JsonIgnore
	public Aluguel toEntity() {
		Aluguel aluguel = new Aluguel();
		aluguel.setId(id);
		aluguel.setCliente(cliente.toEntity());
		aluguel.setCarros(carros.stream().map(CarroPojo::toEntity).collect(Collectors.toList()));
		
		return aluguel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClientePojo getCliente() {
		return cliente;
	}

	public void setCliente(ClientePojo cliente) {
		this.cliente = cliente;
	}

	public List<CarroPojo> getCarros() {
		return carros;
	}

	public void setCarros(List<CarroPojo> carros) {
		this.carros = carros;
	}

	
	
}
