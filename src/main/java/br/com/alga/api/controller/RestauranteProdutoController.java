package br.com.alga.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alga.api.assembler.ProdutoDTOAssembler;
import br.com.alga.api.assembler.ProdutoDTODisassembler;
import br.com.alga.api.domain.model.Produto;
import br.com.alga.api.domain.model.Restaurante;
import br.com.alga.api.domain.repository.ProdutoRepository;
import br.com.alga.api.domain.service.CadastroProdutoService;
import br.com.alga.api.domain.service.CadastroRestauranteService;
import br.com.alga.api.model.dto.ProdutoDTO;
import br.com.alga.api.model.input.ProdutoInput;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {
	 
		@Autowired
	    private ProdutoRepository produtoRepository;
	    
	    @Autowired
	    private CadastroProdutoService cadastroProduto;
	    
	    @Autowired
	    private CadastroRestauranteService cadastroRestaurante;
	    
	    @Autowired
	    private ProdutoDTOAssembler ProdutoDTOAssembler;
	    
	    @Autowired
	    private ProdutoDTODisassembler produtoInputDisassembler;
	    
	    @GetMapping
	    public List<ProdutoDTO> listar(@PathVariable Long restauranteId, 
	    		@RequestParam(required = false) boolean incluirInativos) {
	        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
	        
	        List<Produto> todosProdutos =  null;
	        
	        if(incluirInativos) {
	        	todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
	        }else {
	        	todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
	        }
	        
	        return ProdutoDTOAssembler.toCollectionModel(todosProdutos);
	    }
	    
	    @GetMapping("/{produtoId}")
	    public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
	        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
	        
	        return ProdutoDTOAssembler.toModel(produto);
	    }
	    
	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public ProdutoDTO adicionar(@PathVariable Long restauranteId,
	            @RequestBody @Valid ProdutoInput produtoInput) {
	        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
	        
	        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
	        produto.setRestaurante(restaurante);
	        
	        produto = cadastroProduto.salvar(produto);
	        
	        return ProdutoDTOAssembler.toModel(produto);
	    }
	    
	    @PutMapping("/{produtoId}")
	    public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
	            @RequestBody @Valid ProdutoInput produtoInput) {
	        Produto produtoAtual = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
	        
	        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
	        
	        produtoAtual = cadastroProduto.salvar(produtoAtual);
	        
	        return ProdutoDTOAssembler.toModel(produtoAtual);
	    }   
}
