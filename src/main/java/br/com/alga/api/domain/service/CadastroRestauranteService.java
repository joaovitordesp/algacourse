package br.com.alga.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alga.api.domain.exception.RestauranteNaoEncontradoException;
import br.com.alga.api.domain.model.Cozinha;
import br.com.alga.api.domain.model.Restaurante;
import br.com.alga.api.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);

		restaurante.setCozinha(cozinha); //Se chegou aqui é pq tem cozinha
		
		return restauranteRepository.save(restaurante);
	}
	
	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
			.orElseThrow( () -> new RestauranteNaoEncontradoException(restauranteId));
	}
}
