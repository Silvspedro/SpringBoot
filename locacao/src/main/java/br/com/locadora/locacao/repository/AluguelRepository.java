package br.com.locadora.locacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.locadora.locacao.entidade.Aluguel;


public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

}
