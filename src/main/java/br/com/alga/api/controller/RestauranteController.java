package br.com.alga.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alga.api.domain.exception.CozinhaNaoEncontradaException;
import br.com.alga.api.domain.exception.NegocioException;
import br.com.alga.api.domain.model.Cozinha;
import br.com.alga.api.domain.model.Restaurante;
import br.com.alga.api.domain.repository.RestauranteRepository;
import br.com.alga.api.domain.service.CadastroRestauranteService;
import br.com.alga.api.model.dto.CozinhaDTO;
import br.com.alga.api.model.dto.RestauranteDTO;
import br.com.alga.api.model.input.RestauranteInput;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	RestauranteRepository restauranteRepository;

	@Autowired
	CadastroRestauranteService cadastroRestaurante;

	@GetMapping
	public List<RestauranteDTO> listar() {
		return toCollectionDTO(restauranteRepository.findAll());

	}

	@GetMapping("/{restauranteId}")
	public RestauranteDTO buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		return toModell(restaurante);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restaurante = toDomainObject(restauranteInput);

			return toModell(cadastroRestaurante.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public RestauranteDTO atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restaurante = toDomainObject(restauranteInput);
			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

			BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
					"produtos");

			return toModell(cadastroRestaurante.salvar(restauranteAtual));

		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	private RestauranteDTO toModell(Restaurante restaurante) {
		RestauranteDTO restauranteDTO = new RestauranteDTO();
		CozinhaDTO cozinhaDTO = new CozinhaDTO();

		cozinhaDTO.setId(restaurante.getCozinha().getId());
		cozinhaDTO.setNome(restaurante.getCozinha().getNome());

		restauranteDTO.setRestauranteId(restaurante.getId());
		restauranteDTO.setNome(restaurante.getNome());
		restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());
		restauranteDTO.setCozinha(cozinhaDTO);
		return restauranteDTO;
	}

	private List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> toModell(restaurante)).collect(Collectors.toList());
	}

	private Restaurante toDomainObject(RestauranteInput restauranteInput) { // converte o Restaurante Input em
																			// Restaurante
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteInput.getCozinha().getId());

		restaurante.setCozinha(cozinha);

		return restaurante;
	}
}
