package br.com.alga.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alga.api.domain.model.Produto;
import br.com.alga.api.model.dto.ProdutoDTO;

@Component
public class ProdutoDTOAssembler {
	
		@Autowired
	    private ModelMapper modelMapper;
	    
	    public ProdutoDTO toModel(Produto produto) {
	        return modelMapper.map(produto, ProdutoDTO.class);
	    }
	    
	    public List<ProdutoDTO> toCollectionModel(List<Produto> produtos) {
	        return produtos.stream()
	                .map(produto -> toModel(produto))
	                .collect(Collectors.toList());
	    }   
}