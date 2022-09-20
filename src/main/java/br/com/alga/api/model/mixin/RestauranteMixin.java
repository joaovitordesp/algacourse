package br.com.alga.api.model.mixin;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.alga.api.domain.model.Cozinha;
import br.com.alga.api.domain.model.Endereco;
import br.com.alga.api.domain.model.FormaPagamento;
import br.com.alga.api.domain.model.Produto;

public abstract class RestauranteMixin {
	
	@JsonIgnoreProperties(value ="nome", allowGetters = true) //allowGetters -> nao vai ignorar quando serializar em json
	private Cozinha cozinha;
	
	@JsonIgnore
	private Endereco endereco;
	
	@JsonIgnore
	private OffsetDateTime dataCadastro;
	
	@JsonIgnore
	private OffsetDateTime dataAtualizacao;
	
	@JsonIgnore
	private List<FormaPagamento> formasPagamento;

	@JsonIgnore
	private List<Produto> produto;

}
