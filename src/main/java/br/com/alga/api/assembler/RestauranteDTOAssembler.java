package br.com.alga.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alga.api.domain.model.Restaurante;
import br.com.alga.api.model.dto.RestauranteDTO;

@Component
public class RestauranteDTOAssembler {
	//Assembler == montador
	
	@Autowired
	private ModelMapper modelMapper;

	public RestauranteDTO toModell(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteDTO.class);
	}

	public List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> toModell(restaurante)).collect(Collectors.toList());
	}
	
}
