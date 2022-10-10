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

import br.com.alga.api.assembler.EstadoDTOAssembler;
import br.com.alga.api.assembler.EstadoInputDisasembler;
import br.com.alga.api.domain.model.Estado;
import br.com.alga.api.domain.repository.EstadoRepository;
import br.com.alga.api.domain.service.CadastroEstadoService;
import br.com.alga.api.model.dto.EstadoDTO;
import br.com.alga.api.model.input.EstadoInput;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	@Autowired
	private EstadoDTOAssembler estadoDTOAssembler;

	@Autowired
	private EstadoInputDisasembler estadoInputDisasembler;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EstadoDTO> listar() {
		List<Estado> allEstados = estadoRepository.findAll();

		return estadoDTOAssembler.toCollectionDTO(allEstados);
	}

	@GetMapping("/{estadoId}")
	public EstadoDTO buscar(@PathVariable Long estadoId) {
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId);

		return estadoDTOAssembler.toModell(estado);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoDTO adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = estadoInputDisasembler.toDomainObject(estadoInput);
		estado = cadastroEstado.salvar(estado);

		return estadoDTOAssembler.toModell(estado);
	}

	@PutMapping("/{estadoId}")
	public EstadoDTO atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);

		estadoInputDisasembler.copyToDomainObject(estadoInput, estadoAtual);
		estadoAtual = cadastroEstado.salvar(estadoAtual);

		return estadoDTOAssembler.toModell(estadoAtual);
	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		cadastroEstado.excluir(estadoId);
	}
}
