package br.com.alga.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.alga.api.domain.model.Estado;


public abstract class CidadeMixin{

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Estado estado;
}
