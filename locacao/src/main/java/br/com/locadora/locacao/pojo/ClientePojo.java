package br.com.locadora.locacao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.locadora.locacao.entidade.Cliente;



public class ClientePojo {

	private Long id;
	private String nome;
	
	public ClientePojo(Cliente cliente) {
		
		this.id = cliente.getId();	
		this.nome = cliente.getNome();
	}  
	public ClientePojo() {
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
	
	@JsonIgnore
	public Cliente toEntity() {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(nome);
		return cliente;
	}
	
	
}
