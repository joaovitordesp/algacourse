package br.com.alga.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alga.api.domain.model.Produto;
import br.com.alga.api.model.input.ProdutoInput;

@Component
public class ProdutoDTODisassembler {
	 	@Autowired
	    private ModelMapper modelMapper;
	    
	    public Produto toDomainObject(ProdutoInput produtoInput) {
	        return modelMapper.map(produtoInput, Produto.class);
	    }
	    
	    public void copyToDomainObject(ProdutoInput produtoInput, Produto produto) {
	        modelMapper.map(produtoInput, produto);
	    }   
}