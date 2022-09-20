package br.com.alga.api.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;

import br.com.alga.api.domain.model.Cidade;
import br.com.alga.api.domain.model.Cozinha;
import br.com.alga.api.domain.model.Restaurante;
import br.com.alga.api.model.mixin.CidadeMixin;
import br.com.alga.api.model.mixin.CozinhaMixin;
import br.com.alga.api.model.mixin.RestauranteMixin;

@Component
public class JacksonMixinModule extends SimpleModule{

	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
		setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
	}
}
