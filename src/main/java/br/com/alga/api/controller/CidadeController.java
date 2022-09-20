package br.com.alga.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alga.api.domain.exception.EstadoNaoEncontradoException;
import br.com.alga.api.domain.exception.NegocioException;
import br.com.alga.api.domain.model.Cidade;
import br.com.alga.api.domain.repository.CidadeRepository;
import br.com.alga.api.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable Long cidadeId) {
		return cadastroCidade.buscarOuFalhar(cidadeId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody @Valid Cidade cidade) {
		try {
			return cadastroCidade.salvar(cidade);
		}catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{cidadeId}")
	public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody @Valid Cidade cidade) {
			try {
				Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
				
				BeanUtils.copyProperties(cidade, cidadeAtual, "id");
				return cadastroCidade.salvar(cidadeAtual);
			}catch(EstadoNaoEncontradoException e) {
				throw new NegocioException(e.getMessage(), e);
			}
		}

	@DeleteMapping("/{cidadeAId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeAId) {
		cadastroCidade.excluir(cidadeAId);
	}
	
}
