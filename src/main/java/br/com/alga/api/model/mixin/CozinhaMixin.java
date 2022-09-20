package br.com.alga.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.alga.api.domain.model.Restaurante;

public abstract class CozinhaMixin {
	
	@JsonIgnore // ignore essa propriedade, não serilialize-a quando subir as outras
	private List<Restaurante> restaurantes = new ArrayList<>();
}
