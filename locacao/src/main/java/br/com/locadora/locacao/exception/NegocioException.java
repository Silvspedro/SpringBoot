package br.com.locadora.locacao.exception;

public class NegocioException extends RuntimeException{

	private static final long serialVersionUID = -2247630021327686693L;

	public NegocioException(String mensagem) {
		super(mensagem);
	}
}

