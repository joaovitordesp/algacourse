package br.com.alga.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alga.api.domain.model.Estado;
import br.com.alga.api.model.dto.EstadoDTO;

@Component
public class EstadoDTOAssembler {
	//Assembler == montador
	
	@Autowired
	private ModelMapper modelMapper;

	public EstadoDTO toModell(Estado estado) {
		return modelMapper.map(estado, EstadoDTO.class);
	}

	public List<EstadoDTO> toCollectionDTO(List<Estado> estados) {
		return estados.stream().map(estado -> toModell(estado)).collect(Collectors.toList());
	}
	
}
