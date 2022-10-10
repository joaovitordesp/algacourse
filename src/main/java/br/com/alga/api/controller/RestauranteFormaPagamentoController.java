package br.com.alga.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alga.api.assembler.FormaPagamentoDTOAssembler;
import br.com.alga.api.domain.model.Restaurante;
import br.com.alga.api.domain.service.CadastroRestauranteService;
import br.com.alga.api.model.dto.FormaPagamentoDTO;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamentos")
public class RestauranteFormaPagamentoController {

	@Autowired
	CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;
	
	@GetMapping
	public List<FormaPagamentoDTO> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		return formaPagamentoDTOAssembler.toCollectionModel( restaurante.getFormasPagamento()); 

	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);
	}

	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.associarFormaPagamento(restauranteId, formaPagamentoId);
	}

}
