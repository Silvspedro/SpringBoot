package br.com.locadora.locacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.locadora.locacao.entidade.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>{

	public List<Carro> findByNome (String nome);
}
