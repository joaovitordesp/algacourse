package br.com.alga.api.domain.repository;

import java.util.List;

import br.com.alga.api.domain.model.Restaurante;

public interface RestauranteRepository {
	List<Restaurante> listar();
	
	Restaurante buscar(Long id);

	Restaurante salvar(Restaurante cozinha);
	
	void remover(Restaurante cozinha);
}
