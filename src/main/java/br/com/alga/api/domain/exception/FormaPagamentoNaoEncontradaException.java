package br.com.alga.api.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public FormaPagamentoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public FormaPagamentoNaoEncontradaException(Long formaPagamentoId) {
		this(String.format("Não existe um cadastro de forma de pagameto com código %d", formaPagamentoId));
	}
	
}