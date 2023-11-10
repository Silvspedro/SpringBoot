package br.com.locadora.locacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.locadora.locacao.entidade.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>{

}
