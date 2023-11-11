package br.com.mercadolegal.compra.pojo;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.mercadolegal.compra.entidade.Carrinho;

public class CarrinhoPojo {

	private Long id;
	
	private UsuarioPojo usuario;
	
	private List<ProdutoPojo> produtos;
	
	public CarrinhoPojo() {}
	
	public CarrinhoPojo (Carrinho carrinho) {
		this.id = carrinho.getId();
		this.usuario = new UsuarioPojo(carrinho.getUsuario());
		//transforma um tipo em outro para evitar reescrita de codigo
		this.produtos = carrinho.getProdutos().stream().map(ProdutoPojo:: new).collect(Collectors.toList());
		
	}
	@JsonIgnore
	public Carrinho toEntity() {
		Carrinho carrinho = new Carrinho();
		carrinho.setId(id);
		carrinho.setUsuario(usuario.toEntity());
		carrinho.setProdutos(produtos.stream().map(ProdutoPojo::toEntity).collect(Collectors.toList()));
		
		return carrinho;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioPojo getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioPojo usuario) {
		this.usuario = usuario;
	}

	public List<ProdutoPojo> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoPojo> produtos) {
		this.produtos = produtos;
	}
	
	
}
