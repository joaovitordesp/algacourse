package br.com.alga.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alga.api.domain.model.Cidade;
import br.com.alga.api.domain.model.Cozinha;
import br.com.alga.api.domain.model.Restaurante;
import br.com.alga.api.model.input.RestauranteInput;

@Component
public class RestauranteInputDisasembler {
	//Disasembler = desmontador
	
	@Autowired
	private ModelMapper modelMapper;

	
	public Restaurante toDomainObject(RestauranteInput restauranteInput) { 
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		/* Para evitar um 500 - identifier of an instance of br.com.alga.api.domain.model.Cozinha was altered from 1 to 2
		 * */
		restaurante.setCozinha(new Cozinha());
		
		if(restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}
		
		modelMapper.map(restauranteInput, restaurante);
	}
}
