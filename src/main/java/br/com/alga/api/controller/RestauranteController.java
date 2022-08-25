package br.com.alga.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alga.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.alga.api.domain.model.Restaurante;
import br.com.alga.api.domain.repository.RestauranteRepository;
import br.com.alga.api.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	RestauranteRepository restauranteRepository;

	@Autowired
	CadastroRestauranteService cadastroRestaurante;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
		
	}

	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long restauranteId) {
		Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);

		if (restaurante.isPresent()) {
			return ResponseEntity.ok(restaurante.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = cadastroRestaurante.salvar(restaurante);

			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteAtual = restauranteRepository.findById(restauranteId).orElse(null);

			if (restaurante != null) {
				BeanUtils.copyProperties(restaurante, restauranteAtual, 
						"id", "formasPagamento", "endereco", "dataCadastro", "produtos");

				cadastroRestaurante.salvar(restauranteAtual);
				return ResponseEntity.ok(restauranteAtual);
			}
			return ResponseEntity.notFound().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// metodo que atualiza os campos parcialmentes
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> campos) {
		// Map -> o String seria os valores no payload, e o Object seria o proprio
		// Objeto
		Restaurante restauranteAtual = restauranteRepository
		        .findById(restauranteId).orElse(null);

		if (restauranteAtual == null) {
			return ResponseEntity.notFound().build();
		}

		merge(campos, restauranteAtual); // campos -> campos novos altera os campos atuais

		return atualizar(restauranteId, restauranteAtual);
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper(); // converter java pra json
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

		System.out.println(restauranteOrigem);

		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);

			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}
}
