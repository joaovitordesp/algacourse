package br.com.alga.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alga.api.domain.exception.EntidadeEmUsoException;
import br.com.alga.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.alga.api.domain.model.Cozinha;
import br.com.alga.api.domain.repository.CozinhaRepository;
import br.com.alga.api.domain.service.CadastroCozinhaService;

//@Controller
//@ResponseBody //as respostas do metodo da controller deve ir para a resposta da requisicao
@RestController // Rest Controller faz com que nao use @Controller e @ResponseBody
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> listar() {
		//return cozinhaRepository.listar();
		return cozinhaRepository.findAll();
	}

	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) { // ResponseEntity permiti customizar a
																			// resposta http
		//Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		Optional<Cozinha>  cozinha = cozinhaRepository.findById(cozinhaId);
		
		if (cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Cozinha cozinha) { // @RequestBody diz ao parametro que é ali que
																		// será passado o corpo da requisicao
		try {
			cozinha = cadastroCozinha.salvar(cozinha);
			return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<?> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
			Optional<Cozinha>  cozinhaAtual = cozinhaRepository.findById(cozinhaId);

			if (cozinhaAtual.isPresent()) {
				BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
				/*
				 * 1 parametro - origem : 2 param - destino o copyProperties passa todas as
				 * propriedades para o outro, até mesmo as nulas Por isso, podemos colocar o
				 * terceiro parametro que seria qual que você quer ignorar
				 */
				// cozinhaAtual.setNome(cozinha.getNome());

				Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinhaAtual.get());
				return ResponseEntity.ok(cozinhaSalva);
			} 
				return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> deletar(@PathVariable Long cozinhaId) {
		try {
			cadastroCozinha.excluir(cozinhaId);

			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build(); // CONFLICT - ERRO 409
			// Veremos em outro modulo como tratar a mensagem de erro
		}
	}
}
