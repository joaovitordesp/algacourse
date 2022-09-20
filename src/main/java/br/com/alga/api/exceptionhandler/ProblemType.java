package br.com.alga.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Corpo da mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	DADOS_INVALIDOS("/dados-invalidos", "Os dados passados estão inválidos"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title){
		this.uri = "https://algafood.com.br"+path;
		this.title = title;
	}
}
