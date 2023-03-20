package br.com.alga.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alga.api.domain.model.FotoProduto;
import br.com.alga.api.model.dto.FotoProdutoDTO;

@Component
public class FotoProdutoDTOAssembler {
	//Assembler == montador
	
	@Autowired
	private ModelMapper modelMapper;

	public FotoProdutoDTO toModell(FotoProduto foto) {
		return modelMapper.map(foto, FotoProdutoDTO.class);
	}
	
}
