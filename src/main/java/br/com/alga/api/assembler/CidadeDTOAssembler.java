package br.com.alga.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alga.api.domain.model.Cidade;
import br.com.alga.api.domain.model.Restaurante;
import br.com.alga.api.model.dto.CidadeDTO;
import br.com.alga.api.model.dto.RestauranteDTO;

@Component
public class CidadeDTOAssembler {
	//Assembler == montador
	
	@Autowired
	private ModelMapper modelMapper;

	public CidadeDTO toModell(Cidade cidade) {
		return modelMapper.map(cidade, CidadeDTO.class);
	}

	public List<CidadeDTO> toCollectionDTO(List<Cidade> cidades) {
		return cidades.stream().map(cidade -> toModell(cidade)).collect(Collectors.toList());
	}
	
}
