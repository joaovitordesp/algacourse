package br.com.alga.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import br.com.alga.api.assembler.CidadeDTOAssembler;
import br.com.alga.api.assembler.CidadeInputDisasembler;
import br.com.alga.api.domain.exception.EstadoNaoEncontradoException;
import br.com.alga.api.domain.exception.NegocioException;
import br.com.alga.api.domain.model.Cidade;
import br.com.alga.api.domain.repository.CidadeRepository;
import br.com.alga.api.domain.service.CadastroCidadeService;
import br.com.alga.api.model.dto.CidadeDTO;
import br.com.alga.api.model.input.CidadeInput;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CidadeDTOAssembler cidadeDTOAssembler;

	@Autowired
	private CidadeInputDisasembler cidadeInputDisasembler;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CidadeDTO> listar() {
		List<Cidade> todasCidades = cidadeRepository.findAll();

		return cidadeDTOAssembler.toCollectionDTO(todasCidades);
	}

	@GetMapping("/{cidadeId}")
	public CidadeDTO buscar(@PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
	    
	    return cidadeDTOAssembler.toModell(cidade);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeDTO adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisasembler.toDomainObject(cidadeInput);
	        
	        cidade = cadastroCidade.salvar(cidade);
	        
	        return cidadeDTOAssembler.toModell(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{cidadeId}")
	public CidadeDTO atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
	        
	        cidadeInputDisasembler.copyToDomainObject(cidadeInput, cidadeAtual);
	        
	        cidadeAtual = cadastroCidade.salvar(cidadeAtual);
	        
	        return cidadeDTOAssembler.toModell(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{cidadeAId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeAId) {
		cadastroCidade.excluir(cidadeAId);
	}

}
