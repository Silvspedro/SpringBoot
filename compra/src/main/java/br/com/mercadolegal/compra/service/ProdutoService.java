package br.com.mercadolegal.compra.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.mercadolegal.compra.entidade.Produto;
import br.com.mercadolegal.compra.exception.NegocioException;
import br.com.mercadolegal.compra.pojo.ProdutoPojo;
import br.com.mercadolegal.compra.repository.ProdutoRepository;


@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	
	public ProdutoService (ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	public ProdutoPojo salvar(Produto produto) {
		validarProdutos(produto);
		return new ProdutoPojo( produtoRepository.save(produto));
	}
	
	private void validarProdutos(Produto produto) {
		if (produto.getNome() == null) {
			throw new NegocioException("Nome do produto é obrigatório");
		} else if(produto.getCategoria() == null) {
			throw new NegocioException("A categoria do produto é obrigatória");
		} else if(produto.getValor() == null) {
			throw new NegocioException("O valor do produto é obrigatório");
		} else if(produto.getValor() == "0") {
			throw new NegocioException("O valor do produto não pode ser zero");
		}
		
		List<Produto> produtoBd = produtoRepository.findByNome(produto.getNome());
		if(produto != null && !produtoBd.isEmpty()){
			throw new NegocioException("Produto já cadastrado");
	}
	
	}
	
	public List<ProdutoPojo> recuperarProdutos(){
		List<Produto> listProduto = produtoRepository.findAll();
		List<ProdutoPojo> listProdutoPojo = new ArrayList<>();
		for (Produto produto : listProduto) {
			listProdutoPojo.add(new ProdutoPojo(produto));
		}
		return listProdutoPojo;
	}
	
	public ProdutoPojo recuperarProduto(Long id) {
		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		if (produtoOptional.isPresent()) {
			return new ProdutoPojo(produtoOptional.get());
		}
		return null;
	}
	
	public void deletarProduto(Long id) {
		produtoRepository.deleteById(id);
	}
}
