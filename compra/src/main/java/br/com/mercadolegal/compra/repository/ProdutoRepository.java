package br.com.mercadolegal.compra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.mercadolegal.compra.entidade.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public List<Produto> findByNome (String nome);
	
}
