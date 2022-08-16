package br.com.alga.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alga.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.alga.api.domain.model.Cozinha;
import br.com.alga.api.domain.model.Restaurante;
import br.com.alga.api.domain.repository.CozinhaRepository;
import br.com.alga.api.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Não existe cozinha com o código %d", cozinhaId)));

		restaurante.setCozinha(cozinha); //Se chegou aqui é pq tem cozinha
		
		return restauranteRepository.save(restaurante);
	}
}
