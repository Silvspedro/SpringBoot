package br.com.locadora.locacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.locadora.locacao.entidade.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
