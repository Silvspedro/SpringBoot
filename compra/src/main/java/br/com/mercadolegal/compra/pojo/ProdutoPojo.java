package br.com.mercadolegal.compra.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.mercadolegal.compra.entidade.Produto;


public class ProdutoPojo {
	private Long id;
	private String nome;
	private String categoria;
	private String valor;
	
	public ProdutoPojo(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.categoria = produto.getCategoria();
		this.valor = produto.getValor();
	}
	public ProdutoPojo() {
		
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
	public Produto toEntity() {
		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome(nome);
		produto.setCategoria(categoria);
		produto.setValor(valor);
		return produto;

	}
	

}
