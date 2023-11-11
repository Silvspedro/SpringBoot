package br.com.locadora.locacao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.locadora.locacao.entidade.Pagamento;

public class PagamentoPojo {

	private Long id;
	
	private AluguelPojo aluguel;
	
	private boolean RealizadoPagamento;
	
	public PagamentoPojo() {}
	
	public PagamentoPojo (Pagamento pagamento) {
		this.id = pagamento.getId();
		this.aluguel  = new AluguelPojo(pagamento.getAluguel());
		this.RealizadoPagamento = (pagamento.isRealizadoPagamento());
	} 
	
	@JsonIgnore
	public Pagamento toEntity() {
		Pagamento pagamento = new Pagamento();
		pagamento.setId(id);
		pagamento.setAluguel(aluguel.toEntity());	
		pagamento.setRealizadoPagamento(RealizadoPagamento);
		return pagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AluguelPojo getAluguel() {
		return aluguel;
	}

	public void setAluguel(AluguelPojo aluguel) {
		this.aluguel = aluguel;
	}

	public boolean isRealizadoPagamento() {
		return RealizadoPagamento;
	}

	public void setRealizadoPagamento(boolean realizadoPagamento) {
		RealizadoPagamento = realizadoPagamento;
	}

	
	
	
}
