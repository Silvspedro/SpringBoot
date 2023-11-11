package br.com.locadora.locacao.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	private Aluguel aluguel;
	private boolean RealizadoPagamento;

	public Pagamento () {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isRealizadoPagamento() {
		return RealizadoPagamento;
	}

	public void setRealizadoPagamento(boolean realizadoPagamento) {
		RealizadoPagamento = realizadoPagamento;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	
	
}
