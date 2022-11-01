package br.com.alga.api.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.alga.api.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDTO {
	
	@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private Long restauranteId;

	@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private String nome;
	
	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@JsonView(RestauranteView.Resumo.class)
	private CozinhaDTO cozinha;
	
	private Boolean ativo;
	private EnderecoDTO endereco;
	private Boolean aberto;
}
