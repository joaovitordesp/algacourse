package br.com.alga.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alga.api.domain.model.Cozinha;
import br.com.alga.api.model.dto.CozinhaDTO;

@Component
public class CozinhaDTOAssembler {
	//Assembler == montador
	
	@Autowired
	private ModelMapper modelMapper;

	public CozinhaDTO toModell(Cozinha cozinha) {
		return modelMapper.map(cozinha, CozinhaDTO.class);
	}

	public List<CozinhaDTO> toCollectionDTO(List<Cozinha> cozinhas) {
		return cozinhas.stream().map(cozinha -> toModell(cozinha)).collect(Collectors.toList());
	}
	
}
