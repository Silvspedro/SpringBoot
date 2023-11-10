package br.com.locadora.locacao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.locadora.locacao.entidade.Carro;

public class CarroPojo {

	private Long id;
	private String nome;
	private String categoria;
	private String valor;
	
	public CarroPojo(Carro carro) {
		
		this.id = carro.getId();	
		this.nome = carro.getNome();
		this.categoria = carro.getCategoria();
		this.valor = carro.getValor();
	}  
	public CarroPojo() {
	}
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@JsonIgnore
	public Carro toEntity() {
		Carro carro = new Carro();
		carro.setId(id);
		carro.setNome(nome);
		carro.setCategoria(categoria);
		carro.setValor(valor);
		return carro;

	}
	
	
}
